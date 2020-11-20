package persistence;

import org.json.*;

// Represents an interface that contains the toJson method
public interface Writable {
    JSONObject toJson();
}

// CITATIONS
// Took chunks of sample code from JsonSerializationDemo