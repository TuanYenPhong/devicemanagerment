package com.deviceomi.search;

import com.deviceomi.model.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class DevicePersonSearch {
    /**
     *   Ma thiet bi
     */
    private String idDevice;

    /**
     *   Loai thiet bi
     */
    private String typeDevice;

    /**
     * Thiet bi di kem
     */
    private String deviceAttach;

    /**
     * User
     */
    private Long idUser;

    /**
     *tu ngay cap nhat
     */
    private Date fromModifiedDate;

    /**
     *tu ngay cap nhat
     */
    private Date toModifiedDate;
}
