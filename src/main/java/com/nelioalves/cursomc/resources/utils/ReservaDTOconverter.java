package com.nelioalves.cursomc.resources.utils;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.nelioalves.cursomc.dto.ReservaDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservaDTOconverter {
	
    public static List<ReservaDTO> convertProductMapToList(Map<String, Map<String, Object>> productMap) {
        List<ReservaDTO> products = new ArrayList<>();

        for (Map.Entry<String, Map<String, Object>> entry : productMap.entrySet()) {
            try {
                Map<String, Object> productData = entry.getValue();
                Integer tipoComponenteId = Integer.parseInt(productData.get("tipoComponenteId").toString());
                Integer experimentoId = Integer.parseInt(productData.get("experimentoId").toString());
                Integer quantidade = Integer.parseInt(productData.get("quantidade").toString());

                ReservaDTO product = new ReservaDTO();
                product.setTipoComponenteId(tipoComponenteId);
                product.setExperimentoId(experimentoId);
                product.setQuantidade(quantidade);

                products.add(product);
            } catch (NumberFormatException | NullPointerException e) {
                // Handle invalid product data here.
                // For example, you could log the error or skip the product.
            }
        }

        return products;
    }
}
