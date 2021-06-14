package com.pablom.imatiaorders.repository.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum PreviousStatus {
    RECOGIDA_ALMACEN,
    EN_REPARTO,
    INCIDENCIA_EN_ENTREGA,
    ENTREGADO,
    NONE;

    public static PreviousStatus getPreviousId(final Integer id) {
        switch (id) {
            case 1:
                return RECOGIDA_ALMACEN;
            case 2:
                return EN_REPARTO;
            case 3:
                return INCIDENCIA_EN_ENTREGA;
            case 4:
                return ENTREGADO;
            default:
                return NONE;
        }
    }
}