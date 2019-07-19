package sk.msvitok.app.tools;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

public final class ResponseFetcher {

    private static final Log log = LogFactory.getLog(ResponseFetcher.class);

    private ResponseFetcher() {}

    public static String tryFetch(String url, String fallbackValue) {
        try {
            return fetch(url);

        } catch (IOException | ResponseNotOkException e) {
            log.error("Unable to fetch json from URL '" + url+ "'", e);
            return fallbackValue;
        }
    }

    public static String fetch(String url)
            throws IOException, ResponseNotOkException
    {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            int status = response.code();
            if (status != 200) {
                throw new ResponseNotOkException(url, status);
            }
            // body is always non-null when returned from Call#execute (see JavaDoc)
            ResponseBody body = response.body();
            return body.string();
        }
    }

    /* ---------------------------- inner-class ----------------------------- */

    public static class ResponseNotOkException extends Exception {

        private final String url;
        private int status;

        public ResponseNotOkException(String url, int status) {
            super("Invalid response");
            this.url = url;
            this.status = status;
        }

        public String getUrl() { return url; }
        public int getStatus() { return status; }
    }
}
