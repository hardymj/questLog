package uk.co.tpplc.google;

public class GoogleSSO {
	/*HttpClient client = new HttpClient();
    String url = "https://accounts.google.com/o/oauth2/token";
    PostMethod post = new PostMethod(url);
    post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    try {
        post.setRequestEntity(new StringRequestEntity(foros, null, null));
    } catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);
    }
    String accessToken = null;
    try {
        client.executeMethod(post);
        String resp = post.getResponseBodyAsString();
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(resp);
        JSONObject parsed = (JSONObject)obj;            
        accessToken = (String) parsed.get("access_token");
    } catch (HttpException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (ParseException e) {
        throw new RuntimeException(e);
    }
    
    GetMethod getUserInfo = new GetMethod("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+accessToken);

    String googleId = null;
    String email = null;
    String name = null;
    String firstName = null;
    String lastName = null;
    try {
        client.executeMethod(getUserInfo);
        String resp = getUserInfo.getResponseBodyAsString();
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(resp);
        JSONObject parsed = (JSONObject)obj;
        googleId = (String) parsed.get("id");
        email = (String) parsed.get("email");
        name = (String) parsed.get("name");
        firstName = (String) parsed.get("given_name");
        lastName = (String) parsed.get("family_name");
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (ParseException e) {
        throw new RuntimeException(e);
    }*/
}
