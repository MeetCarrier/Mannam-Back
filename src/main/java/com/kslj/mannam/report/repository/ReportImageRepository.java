package com.kslj.mannam.report.repository;

import com.kslj.mannam.report.entity.ReportImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportImageRepository extends JpaRepository<ReportImage, Long> {
}
