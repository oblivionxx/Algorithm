import java.util.*;

/*
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

Hash Table, Math
 */
public class LT535_Encode_and_Decode_TinyURL {
    // incremental
    Map<Integer, String> map = new HashMap<>();
    int i = 0;

    public String encode(String longUrl) {
	map.put(i, longUrl);
	return "http://tinyurl.com/" + i++;
    }

    public String decode(String shortUrl) {
	return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }

    // hashcode
    Map<Integer, String> map2 = new HashMap<>();

    public String encode2(String longUrl) {
	map2.put(longUrl.hashCode(), longUrl);
	return "http://tinyurl.com/" + longUrl.hashCode();
    }

    public String decode2(String shortUrl) {
	return map2.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }
}
