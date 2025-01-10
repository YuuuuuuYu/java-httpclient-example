import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientExample {
    public static void main(String[] args) {
        HttpClientExample httpClientExample = new HttpClientExample();
        String requestUrl = "https://nid.naver.com/oauth2.0/authorize";
        String redirectUrl = "https://4d4cat.site/login/naver/callback";
        String clientKey = ""; // 네이버 키값

        NaverLoginRequest requestParam = new NaverLoginRequest("code", clientKey, redirectUrl, "state");
        try {
            httpClientExample.get(requestUrl, requestParam.toString());
            httpClientExample.getAsync(requestUrl, requestParam.toString());
            httpClientExample.post(requestUrl, requestParam.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get(String uri, String param) throws Exception {
        String uriWithParam = uri+"?"+param;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriWithParam))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Sync status code: "+ response.statusCode());
    }

    public void getAsync(String uri, String param) {
        String uriWithParam = uri+"?"+param;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriWithParam))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::statusCode)
                .thenAccept(statusCode -> System.out.println("Async status code: " + statusCode))
                .join();
    }

    public void post(String uri, String data) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println("Post status code: " + response.statusCode());
    }
}