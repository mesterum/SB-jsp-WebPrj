package mesterum.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mesterum.models.Result;
import mesterum.models.ResultPK;

public interface ResultRepository extends JpaRepository<Result, ResultPK> {
@Query("select r from Result r order by studentId, courseId")
Stream<Result> readAll();
}
