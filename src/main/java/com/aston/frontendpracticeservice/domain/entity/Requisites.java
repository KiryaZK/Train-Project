package com.aston.frontendpracticeservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requisites")
public class Requisites {

    @Id
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @Column(name = "account_number", nullable = false, unique = true, length = 20)
    private String accountNumber;

    @Column(name = "bic", nullable = false, length = 9)
    private String bic;

    @Column(name = "correspondent_account", nullable = false, length = 20)
    private String correspondentAccount;

    @Column(name = "number_inn", nullable = false, unique = true, length = 12)
    private String numberInn;

    @Column(name = "kpp", nullable = false, length = 9)
    private String kpp;

    @Column(name = "kbk", length = 20)
    private String kbk;

    @PrePersist
    public void prePersist() {
        if (bic == null) {
            bic = "044525225";
        }
        if (correspondentAccount == null) {
            correspondentAccount = "30101810700000000187";
        }
        if (kpp == null) {
            kpp = "773601001";
        }
    }

    public void setUser(User user) {
        user.setRequisites(this);
        this.user = user;
        id = user.getId();
    }
}
