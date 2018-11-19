package ch.efg.sample.model;

import ch.efg.sample.api.IUser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class User implements IUser, Comparable {

    @EqualsAndHashCode.Include
    public String id;

    public String name;

    public String groupId;

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}