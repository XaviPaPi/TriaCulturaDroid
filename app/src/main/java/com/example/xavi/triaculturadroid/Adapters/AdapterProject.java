package com.example.xavi.triaculturadroid.Adapters;

import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.xavi.triaculturadroid.Data.Model.File;
import com.example.xavi.triaculturadroid.Data.Model.Project;
import com.example.xavi.triaculturadroid.Data.Model.User;
import com.example.xavi.triaculturadroid.Data.Model.Vote;
import com.example.xavi.triaculturadroid.Data.Model.userTransfer;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;
import com.example.xavi.triaculturadroid.R;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by miquel on 2/22/2018.
 */
public class AdapterProject extends BaseAdapter {

    List<Project> model;
    List<Vote> votes;
    Context context;
    Project projct;
    userTransfer user;
    User usuari;
    String dniUser;
    int mode;
    int id_vote;
    TextView LIP_textAuthor, LIP_textDescript, LIP_textTitle, LIP_textDescriptComplert;
    Button LIP_btnVote;
    static boolean votat;
    static int idProjecVotat;
    static Vote voteUser;
    static ImageView LIP_image1, LIP_image2, LIP_image3;
    ArrayList<Button> arrButons;
    static int id_file;
    List<File> files_from_proj = new ArrayList<>();
    ProgressBar pb_audio;
    MediaPlayer mp;
    ImageButton btnplay;
    PopupWindow show_file_window;
    File file;
    public AdapterProject(Context context, List<Project> model, userTransfer user) {
        arrButons = new ArrayList<>();
        this.user = user;
        this.model = model;
        mode = 1;
        this.context = context;

        votes = APIUtils.get_votes(user.getId());
        if (votes != null && votes.size() > 0) {
            Vote vote = votes.get(votes.size() - 1);
            for (Project p : model)
                if (p.getId() == vote.getProj_id()) {
                    votat = true;
                    idProjecVotat = p.getId();
                    id_vote = vote.getId();
                }
        }
        Log.d(TAG, "ADAPTER COUNT: " + model.size());
    }

    public AdapterProject(Context context, List<Project> model, String dni) {
        arrButons = new ArrayList<>();
        this.dniUser = dni;
        this.model = model;
        mode = 1;
        this.context = context;
        usuari = APIUtils.get_user_by_dni(dniUser);
        user = new userTransfer(usuari);
        votes = APIUtils.get_votes(user.getId());
        if (votes != null && votes.size() > 0) {
            Vote vote = votes.get(votes.size() - 1);
            for (Project p : model)
                if (p.getId() == vote.getProj_id()) {
                    votat = true;
                    idProjecVotat = p.getId();
                    id_vote = vote.getId();
                }
        }
        Log.d(TAG, "ADAPTER COUNT: " + model.size());
    }

    public AdapterProject(Context context, Project project) {

        this.projct = project;
        this.context = context;
        mode = 0;
    }

    @Override
    public int getCount() {
        if (mode == 1)
            return model.size();
        else
            return 1;
    }

    @Override
    public Project getItem(int position) {
        if (mode == 1) {
            return model.get(position);
        } else {
            return projct;
        }
    }

    @Override
    public long getItemId(int position) {
        if (mode == 1)
            return getItem(position).getId();
        else
            return 0;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflator != null) {
                convertView = inflator.inflate(R.layout.activity_item_list_projects, parent, false);
            }
        }

        final List<File> file_list_from_project = APIUtils.get_files_from_project(model.get(position));

        if (convertView != null) {
            LIP_textTitle = (TextView) convertView.findViewById(R.id.ILP_Title);
            LIP_textDescript = (TextView) convertView.findViewById(R.id.ILP_DescriptionLimitat);
            LIP_textDescriptComplert = (TextView) convertView.findViewById(R.id.ILP_DescriptionComplert);
            LIP_textAuthor = (TextView) convertView.findViewById(R.id.ILP_AuthorName);
            LIP_btnVote = (Button) convertView.findViewById(R.id.ILP_Bnt_vote);
            LIP_image1 = (ImageView) convertView.findViewById(R.id.ILP_imageView1);
            LIP_image2 = (ImageView) convertView.findViewById(R.id.ILP_imageView2);
            LIP_image3 = (ImageView) convertView.findViewById(R.id.ILP_imageView3);
        }

        if (!arrButons.contains(LIP_btnVote))
            arrButons.add(LIP_btnVote);


        LIP_textTitle.setText(model.get(position).getTitle());
        LIP_textDescript.setText(model.get(position).getDescript());
        LIP_textDescriptComplert.setText(model.get(position).getDescript());
        LIP_textAuthor.setText(model.get(position).getAuthor().getName() + " " + model.get(position).getAuthor().getSurname());
//region listeners image_buttons
        LIP_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_file = file_list_from_project.get(0).getId();
                dl_file(view);
            }
        });
        LIP_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_file = file_list_from_project.get(1).getId();
                dl_file(view);
            }
        });
        LIP_image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_file = file_list_from_project.get(2).getId();
                dl_file(view);
            }
        });
//endregion
        //region set icons on buttons
        //primer file
        if (file_list_from_project.get(0).getExtension().equalsIgnoreCase(".jpg")
                || file_list_from_project.get(0).getExtension().equalsIgnoreCase(".png")
                || file_list_from_project.get(0).getExtension().equalsIgnoreCase(".gif")
                || file_list_from_project.get(0).getExtension().equalsIgnoreCase(".jpeg")
                ) {
            LIP_image1.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_image));
        } else if (file_list_from_project.get(0).getExtension().equalsIgnoreCase(".mp3")
                || file_list_from_project.get(0).getExtension().equalsIgnoreCase(".ogg")) {
            LIP_image1.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_music));
        } else if (file_list_from_project.get(0).getExtension().equalsIgnoreCase(".3gp")
                || file_list_from_project.get(0).getExtension().equalsIgnoreCase(".mp4")
                || file_list_from_project.get(0).getExtension().equalsIgnoreCase(".webm")
                ) {
            LIP_image1.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_video));
        }
        //segon file
        if (file_list_from_project.get(1).getExtension().equalsIgnoreCase(".jpg")
                || file_list_from_project.get(1).getExtension().equalsIgnoreCase(".png")
                || file_list_from_project.get(1).getExtension().equalsIgnoreCase(".gif")
                || file_list_from_project.get(1).getExtension().equalsIgnoreCase(".jpeg")
                ) {
            LIP_image2.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_image));
        } else if (file_list_from_project.get(1).getExtension().equalsIgnoreCase(".mp3")
                || file_list_from_project.get(1).getExtension().equalsIgnoreCase(".ogg")) {
            LIP_image2.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_music));
        } else if (file_list_from_project.get(1).getExtension().equalsIgnoreCase(".3gp")
                || file_list_from_project.get(1).getExtension().equalsIgnoreCase(".mp4")
                || file_list_from_project.get(1).getExtension().equalsIgnoreCase(".webm")
                ) {
            LIP_image2.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_video));
        }
        //tercer file
        if (file_list_from_project.get(2).getExtension().equalsIgnoreCase(".jpg")
                || file_list_from_project.get(2).getExtension().equalsIgnoreCase(".png")
                || file_list_from_project.get(2).getExtension().equalsIgnoreCase(".gif")
                || file_list_from_project.get(2).getExtension().equalsIgnoreCase(".jpeg")
                ) {
            LIP_image3.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_image));
        } else if (file_list_from_project.get(2).getExtension().equalsIgnoreCase(".mp3")
                || file_list_from_project.get(2).getExtension().equalsIgnoreCase(".ogg")) {
            LIP_image3.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_music));
        } else if (file_list_from_project.get(2).getExtension().equalsIgnoreCase(".3gp")
                || file_list_from_project.get(2).getExtension().equalsIgnoreCase(".mp4")
                || file_list_from_project.get(2).getExtension().equalsIgnoreCase(".webm")
                ) {
            LIP_image3.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_video));
        }//
//endregion
        if (votat && idProjecVotat != model.get(position).getId()) {
            LIP_btnVote.setEnabled(false);
        } else {
            LIP_btnVote.setEnabled(true);
        }


        final View finalConvertView = convertView;
        //region onclick text description
        LIP_textDescript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalConvertView != null) {
                    LIP_textDescript = finalConvertView.findViewById(R.id.ILP_DescriptionLimitat);
                    LIP_textDescriptComplert = finalConvertView.findViewById(R.id.ILP_DescriptionComplert);
                }
                LIP_textDescript.setVisibility(View.GONE);
                LIP_textDescriptComplert.setVisibility(View.VISIBLE);
            }
        });
        LIP_textDescriptComplert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalConvertView != null) {
                    LIP_textDescript = finalConvertView.findViewById(R.id.ILP_DescriptionLimitat);
                    LIP_textDescriptComplert = finalConvertView.findViewById(R.id.ILP_DescriptionComplert);
                }
                LIP_textDescript.setVisibility(View.VISIBLE);
                LIP_textDescriptComplert.setVisibility(View.GONE);
            }
        });
        //endregion
        //region vote
        LIP_btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vote vote = new Vote();
                vote.setProj_id(model.get(position).getId());
                vote.setUser_id(user.getId());

                if (!votat) {
                    for (int i = 0; i < arrButons.size(); i++) {
                        if (i != position) {
                            arrButons.get(i).setEnabled(false);
                            idProjecVotat = model.get(position).getId();
                        }
                    }
                    voteUser = APIUtils.post_new_vote(vote);
                    votat = true;
                } else {
                    vote = APIUtils.get_vote(vote.getUser_id(), vote.getProj_id());
                    APIUtils.delete_vote(vote);
                    votat = false;
                    for (int i = 0; i < arrButons.size(); i++) {
                        if (i != position) {
                            arrButons.get(i).setEnabled(true);
                        }
                    }
                }
            }
        });
        //endregion
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    ProgressDialog pd;

    public void dl_file(View view) {
        View v = view;
        pd = new ProgressDialog(context);
        pd.setIcon(R.mipmap.ic_launcher);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        pd.setMessage("Carregant...");
        pd.setCancelable(false);
        if (id_file >= 0) {
//            file = APIUtils.get_file_by_id(id_file, pd);
            pd.show();
            Observable<File> fileObservable = APIUtils.getApiService().getFileById(id_file);
            fileObservable.subscribeOn(Schedulers.io())
                    //.doOnSubscribe(pd::show)
                    .subscribe(new Subscriber<File>() {
                        @Override
                        public void onCompleted() {
                            pd.dismiss();
                            makepopupwindow(v);
                        }

                        @Override
                        public void onError(Throwable e) {
                            pd.dismiss();
//                Log.d(TAG, "onError: "+e.toString());
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(File f) {
                            file = f;
                        }
                    });

        }
    }

    public void make_temp_file(byte[] buf, String path) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(buf);
        FileOutputStream fos = new FileOutputStream(path);
        byte[] b = new byte[1024];

        for (int readNum; (readNum = bis.read(b)) != -1; ) {
            fos.write(b, 0, readNum);
        }
    }

    public void pause_audio() {
        if (mp.isPlaying()) {
            mp.pause();
        }
    }

    public void play_audio() {
        if (!mp.isPlaying()) {
            mp.start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (mp.isPlaying()) {
                        int cur_step = mp.getCurrentPosition() * 100 / mp.getDuration();
                        pb_audio.setProgress(cur_step);
                    }
                }
            }).start();
        }

    }

private class GetFileAsyn extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {

        return null;
    }
}

public void makepopupwindow(View view) {

    String temp_path = context.getCacheDir().getPath() + "/FILE";
    byte[] fitxer = Base64.decode(file.getFile_content(), Base64.DEFAULT);
    if (file.getExtension().equalsIgnoreCase(".jpg")
            || file.getExtension().equalsIgnoreCase(".jpeg")
            || file.getExtension().equalsIgnoreCase(".gif")
            || file.getExtension().equalsIgnoreCase(".png")) {
        // popupwindow becomes image
        Bitmap image = BitmapFactory.decodeByteArray(fitxer, 0, fitxer.length);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View imgview = null;
        if (inflater != null) {
            imgview = inflater.inflate(R.layout.image_dialog, null);
        }
        ImageView popupimageView = null;
        if (imgview != null) {
            popupimageView = imgview.findViewById(R.id.dialog_imageview);
            popupimageView.setImageBitmap(image);
        }
        show_file_window = new PopupWindow(imgview, RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        show_file_window.setFocusable(true);
        show_file_window.setBackgroundDrawable(new BitmapDrawable());
        show_file_window.setOutsideTouchable(true);
        show_file_window.update();
        show_file_window.showAtLocation(view, Gravity.CENTER, 0, 0);

    } else if (file.getExtension().equalsIgnoreCase(".mp3")
            || file.getExtension().equalsIgnoreCase(".ogg")) {
        // popupwindow becomes audioplayer
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View audview = inflater.inflate(R.layout.audio_view, null);
        pb_audio = audview.findViewById(R.id.prog_bar_cancion);
        btnplay = audview.findViewById(R.id.btn_play);
        temp_path += file.getExtension();
        try {
            make_temp_file(fitxer, temp_path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Uri uri = Uri.parse(temp_path);
        mp = MediaPlayer.create(context, uri);
        mp.seekTo(0);
        pb_audio.setProgress(0);
        mp.setLooping(false);

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton esto = (ImageButton) view;
                if (!mp.isPlaying()) {
                    esto.setImageResource(android.R.drawable.ic_media_pause);
                    play_audio();
                } else {
                    esto.setImageResource(android.R.drawable.ic_media_play);
                    pause_audio();
                }
            }
        });

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                mediaPlayer.seekTo(0);
                btnplay.setImageResource(android.R.drawable.ic_media_play);

            }
        });

        show_file_window = new PopupWindow(audview, RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        show_file_window.setFocusable(true);
        show_file_window.setBackgroundDrawable(new BitmapDrawable());
        show_file_window.setOutsideTouchable(true);
        show_file_window.update();
        show_file_window.showAtLocation(view, Gravity.CENTER, 0, 0);

    } else if (file.getExtension().equalsIgnoreCase(".3gp")
            || file.getExtension().equalsIgnoreCase(".mp4")
            || file.getExtension().equalsIgnoreCase(".webm")
            ) {
        //popupwindow becomes videoplayer
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vidview = inflater.inflate(R.layout.video_dialog, null);
        VideoView popupvideoView = vidview.findViewById(R.id.dialog_videoview);

        temp_path += file.getExtension();
        try {
            make_temp_file(fitxer, temp_path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        popupvideoView.setVideoPath(temp_path);
        popupvideoView.setMediaController(new MediaController(vidview.getContext()));
        popupvideoView.setVisibility(View.VISIBLE);

//                MediaController vidControl = new MediaController(vidview.getContext());
//                vidControl.setAnchorView(popupvideoView);
//                popupvideoView.setMediaController(vidControl);
        show_file_window = new PopupWindow(vidview, RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        show_file_window.setFocusable(true);
        show_file_window.setBackgroundDrawable(new BitmapDrawable());
        show_file_window.setOutsideTouchable(true);
        show_file_window.update();
        show_file_window.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupvideoView.requestFocus();
    }
}

}
