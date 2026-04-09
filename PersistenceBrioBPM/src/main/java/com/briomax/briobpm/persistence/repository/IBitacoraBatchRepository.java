/**
 * 
 */
package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briomax.briobpm.persistence.entity.BitacoraBatch;
import com.briomax.briobpm.persistence.entity.BitacoraBatchPK;

/**
 * 
 */
public interface IBitacoraBatchRepository extends JpaRepository<BitacoraBatch, BitacoraBatchPK> {

}
