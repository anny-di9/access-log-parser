import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

enum HTTPRequestMethod {
    Unknown,
    GET,
    POST
}

public class LogEntry {
    final String IP;
    final LocalDateTime date;
    final HTTPRequestMethod method;
    final String path;
    final UserAgent userAgent;
    final String responseCode;
    final int responseSize;
    final String referer;

    public LogEntry(String string) {
        int startIndex, endIndex;


        endIndex = string.indexOf('-');
        IP = string.substring(0, endIndex).trim();
        System.out.println("IP: " + IP);

        startIndex = string.indexOf('[') + 1;
        endIndex = string.indexOf(']');
        var formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        date = ZonedDateTime.parse(string.substring(startIndex, endIndex), formatter).toLocalDateTime();
        System.out.println("date: " + date);

        startIndex = string.indexOf('"');
        endIndex = string.indexOf('"', startIndex + 1);
        int savedIndex = endIndex;
        String request = string.substring(startIndex + 1, endIndex);
        System.out.println(request);

        endIndex = request.indexOf(' ');
        String method = request.substring(0, endIndex);
        if (method.equalsIgnoreCase("GET")) {
            this.method = HTTPRequestMethod.GET;
        } else if (method.equalsIgnoreCase("POST")) {
            this.method = HTTPRequestMethod.POST;
        } else {
            this.method = HTTPRequestMethod.Unknown;
        }
        System.out.println("method: " + method);
        startIndex = endIndex;
        endIndex = request.indexOf(' ', endIndex + 1);
        path = request.substring(startIndex, endIndex);
        System.out.println("path: " + path);

        startIndex = string.indexOf(' ', savedIndex + 1);
        endIndex = string.indexOf(' ', startIndex + 1);
        responseCode = string.substring(startIndex, endIndex).trim();
        System.out.println("answerCode: " + responseCode);

        startIndex = endIndex + 1;
        endIndex = string.indexOf(' ', startIndex + 1);
        responseSize = Integer.parseInt(string.substring(startIndex, endIndex));
        System.out.println("answerSize: " + responseSize);

        startIndex = string.indexOf('"', endIndex + 1) + 1;
        endIndex = string.indexOf('"', startIndex + 1);
        String referer = string.substring(startIndex, endIndex);
        if (referer.equals("-") || referer.equals("")) {
            this.referer = "";
        } else {
            this.referer = referer;
        }
        System.out.println("referer: " + this.referer);

        startIndex = string.indexOf('"', endIndex + 1);
        endIndex = string.indexOf('"', startIndex + 1);
        userAgent = new UserAgent(string.substring(startIndex + 1, endIndex));
    }
}