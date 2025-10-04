package com.example.gym.membership;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // Devuelve todos los miembros activos
    List<Member> findByActiveTrue();

    // Devuelve todos los miembros inactivos
    List<Member> findByActiveFalse();

    // Buscar miembro por nombre exacto
    Optional<Member> findByName(String name);

    // Buscar miembros cuyo nombre contenga cierta cadena (case insensitive)
    List<Member> findByNameContainingIgnoreCase(String name);

}
