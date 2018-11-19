package ch.efg.sample.repository;

import ch.efg.sample.api.IUser;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class UserRepository implements IUserRepository<IUser, String> {

    private CopyOnWriteArrayList<IUser> persistance = new CopyOnWriteArrayList<>();

    public List<IUser> findAll() {
        return new ArrayList<>(persistance);
    }

    public List<IUser> findById(String id) {
        return persistance.stream().filter(e -> Objects.equals(e.getId(), id))
                .collect(Collectors.toList());
    }

    public <S extends IUser> List<S> saveAll(Iterable<S> var1) {
        List<S> collectionToSave = new ArrayList<>();
        StreamSupport.stream(var1.spliterator(), false)
                .sequential().collect(Collectors.toCollection(() -> collectionToSave));
        persistance.addAll(collectionToSave);
        return collectionToSave;
    }

    public <S extends IUser> S save(S var1) {
        persistance.add(var1);
        return var1;
    }

    public IUser delete(String var1) {
        Optional<IUser> found = persistance.stream()
                .filter(e -> Objects.equals(e.getId(), var1))
                .findFirst();
        found.ifPresent(e -> persistance.remove(e));
        return found.orElse(null);
    }

    public Map<String, List<IUser>> findAllGroupByGroupId() {
        return persistance.stream().collect(Collectors.groupingBy(IUser::getGroupId));
    }
}
