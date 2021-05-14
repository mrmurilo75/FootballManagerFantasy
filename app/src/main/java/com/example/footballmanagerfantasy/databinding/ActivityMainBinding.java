// Generated by view binder compiler. Do not edit!
package com.example.footballmanagerfantasy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;

import com.example.footballmanagerfantasy.R;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button loadGame;

  @NonNull
  public final ConstraintLayout mainContent;

  @NonNull
  public final Button newGame;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull Button loadGame,
      @NonNull ConstraintLayout mainContent, @NonNull Button newGame) {
    this.rootView = rootView;
    this.loadGame = loadGame;
    this.mainContent = mainContent;
    this.newGame = newGame;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.loadGame;
      Button loadGame = rootView.findViewById(id);
      if (loadGame == null) {
        break missingId;
      }

      ConstraintLayout mainContent = (ConstraintLayout) rootView;

      id = R.id.newGame;
      Button newGame = rootView.findViewById(id);
      if (newGame == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, loadGame, mainContent, newGame);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
