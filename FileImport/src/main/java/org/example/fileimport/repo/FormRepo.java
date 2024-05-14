package org.example.fileimport.repo;

import org.example.fileimport.entity.FormEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepo extends JpaRepository<FormEntity, Integer> {


}
