package mesterum.models;

import java.io.Serializable;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 */
public class StudentPK implements Serializable{
    private short id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = (short) id;
    }

    public StudentPK() {
    }

    public StudentPK(int id) {
        this.id = (short) id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final StudentPK other = (StudentPK) obj;
        return this.id == other.id;
    }
}
