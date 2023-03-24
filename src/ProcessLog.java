public class ProcessLog {
    private String dates;
    private String method;
    private String responseCode;
    private String responseSize;
    private String IP;
    private String elementFirst;
    private String elementSecond;
    private String userAgent;
    private String refUrl;

    public ProcessLog() {
    }

    @Override
    public String toString() {
        return IP + " " +
                elementFirst + " " +
                elementSecond + " " +
                dates + " " +
                "\"" + method + "\"" + " " +
                responseCode + " " +
                responseSize + " " +
                "\"" + refUrl + "\"" + " " +
                "\"" + userAgent + "\"";
    }

    public static ProcessLog of(String str) {
        ProcessLog processLog = new ProcessLog();
        int i;
        i = str.indexOf(' ');
        processLog.IP = str.substring(0, i);
        i++;
        str = str.substring(i);

        i = str.indexOf(' ');
        processLog.elementFirst = str.substring(0, i);
        i++;
        str = str.substring(i);

        i = str.indexOf(' ');
        processLog.elementSecond = str.substring(0, i);
        i++;
        str = str.substring(i);

        i = str.indexOf(']');
        processLog.dates = str.substring(0, i + 1);
        i += 3;
        str = str.substring(i);

        i = str.indexOf('\"');
        processLog.method = str.substring(0, i);
        i += 2;
        str = str.substring(i);

        i = str.indexOf(' ');
        processLog.responseCode = str.substring(0, i);
        i++;
        str = str.substring(i);

        i = str.indexOf(' ');
        processLog.responseSize = str.substring(0, i);
        i += 2;
        str = str.substring(i);

        i = str.indexOf('\"');
        processLog.refUrl = str.substring(0, i);
        i += 3;
        str = str.substring(i);

        i = str.indexOf('\"');
        processLog.userAgent = str.substring(0, i - 1);

        return processLog;
    }

    private String getShortUserAgent() {
        String[] parts = userAgent.split(";");
        String fragment = "";
        if (parts.length >= 2) {
            fragment = parts[1];
        }
        if (fragment != null) {
            int i = fragment.indexOf('/');
            if (i > 0)
                fragment = fragment.substring(0, i);
        }
        assert fragment != null;
        return fragment.trim();
    }

    public boolean isYandexBot() {
        return "YandexBot".equalsIgnoreCase(getShortUserAgent());
    }

    public boolean isGoogleBot() {
        return "GoogleBot".equalsIgnoreCase(getShortUserAgent());
    }
}
