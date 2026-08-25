package it.unicam.cs.mpgc.rpg127083.ui.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public final class FxAsync {
    //prevents instantiation
    private FxAsync(){}

    //Execute an async task, manage its result and possible errors on thread JavaFX.
    public static void execute(CompletableFuture<Void> task, Runnable onSuccess, Consumer<Throwable> onError){
        task.thenRunAsync(onSuccess, Platform::runLater)
                .exceptionallyAsync(ex -> {
                    onError.accept(cleanException(ex));
                    return null;
                }, Platform::runLater);
    }
    private static Throwable cleanException(Throwable ex) {
        return ex.getCause() != null ? ex.getCause() : ex;
    }

    //Overload for tasks that return a value
    public static <T> void execute(CompletableFuture<T> task, Consumer<T> onSuccess, Consumer<Throwable> onError) {
        task.thenAcceptAsync(onSuccess, Platform::runLater)
                .exceptionallyAsync(ex -> {
                    onError.accept(cleanException(ex));
                    return null;
                }, Platform::runLater);
    }

    //Shows a standard error dialog
    public static void showErrorAlert(String title, Throwable ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(ex.getMessage() != null ? ex.getMessage() : "Errore inaspettato");
        alert.showAndWait();
    }
}
