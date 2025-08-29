package summerprojects2025.alarmsystem.utility;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public final class HashUtility {

    public HashUtility() {

    }

    public static String SHA256(String orgString) {
        return Hashing.sha256()
                .hashString(orgString, StandardCharsets.UTF_8)
                .toString();
    }

}
