package ch.efg.sample.repository;

import ch.efg.sample.api.IUser;

import java.util.List;
import java.util.Map;

public interface IUserRepository <T, ID extends String> {

    List<T> findAll();

    List<T> findById(ID id);

    <S extends T> List<S> saveAll(Iterable<S> var1);

    <S extends T> S save(S var1);

    IUser delete(ID var1);

    /**
     * @return {@code Map} of users by groupId
     */
    Map<ID, List<T>> findAllGroupByGroupId();
}
