
package com.fci.colors_app.ui.error_handler;

public interface ErrorHandlerNavigator {

    void showMyApiMessage(String message);

    void handleError(Throwable throwable);

    void showUserDeletedMsg();
}
