package ch.efg.sample.model;

import ch.efg.sample.api.IUser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class User implements IUser {

    // Since id is strictly defined, unclude only this field in equals and hash code.
    @EqualsAndHashCode.Include
    public String id;

    public String name;

    public String groupId;
}
