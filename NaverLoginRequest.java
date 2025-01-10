public class NaverLoginRequest {
    private String response_type;
    private String client_id;
    private String redirect_uri;
    private String state;

    public NaverLoginRequest(String response_type, String client_id, String redirect_uri, String state) {
        this.response_type = response_type;
        this.client_id = client_id;
        this.redirect_uri = redirect_uri;
        this.state = state;
    }

    @Override
    public String toString() {
        return "response_type=" + response_type
                + "&client_id=" + client_id
                + "&redirect_uri=" + redirect_uri
                + "&state=" + state;
    }
}
