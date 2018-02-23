package se.kth.iv1201.boblaghei.util;

/**
 * Defines HTTP paths used by the REST api
 */
public class HttpPath {
    /**
     * Base path for the rest api
     */
    public static final String BASE_PATH = "/api";

    /**
     * Path for the application resource
     */
    public static final String APPLICATIONS_PATH = BASE_PATH + "/applications";

    /**
     * Path for the competence resource
     */
    public static final String COMPETENCES_PATH = BASE_PATH + "/competences";

    /**
     * Path for the user resource
     */
    public static final String USERS_PATH = BASE_PATH + "/users";

    /**
     * The path used to register a person
     */
    public static final String REGISTER_PATH = "/register";

    /**
     * The path used to login
     */
    public static final String LOGIN_PATH = "/login";
}
