package mesterum.models;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 */
public class CoursePK implements Serializable{
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CoursePK(String id) {
        this.id = id;
    }

    public CoursePK() {
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final CoursePK other = (CoursePK) obj;
        return Objects.equals(this.id, other.id);
    }
}
