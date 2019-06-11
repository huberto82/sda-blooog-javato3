package mapper;

public interface Mapper<T, U> {
    T toEntity(U obj);
    U toDomain(T obj);
}
