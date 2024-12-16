package com.apelisser.manager.domain.model;

import com.apelisser.manager.domain.util.Assert;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@ToString
@EqualsAndHashCode
public class EquipmentModel {

    @Getter
    private final Long id;
    private final Set<Long> pieces = new LinkedHashSet<>();

    public EquipmentModel(Long id) {
        Assert.notNull(id, "Id must not be null.");
        this.id = id;
    }

    /**
     * Add a piece id to the existing collection.
     * @param pieceId the piece id that must not be null.
     */
    public void addPiece(Long pieceId) {
        Assert.notNull(pieceId, "Piece id must not be null.");
        pieces.add(pieceId);
    }

    /**
     * Add a collection of piece ids to the existing collection.
     * @param pieceIds a collection of piece ids that must not be empty and must not contain any null value.
     */
    public void addPieces(Set<Long> pieceIds) {
        Assert.notEmpty(pieceIds, "Pieces id list must not be empty.");
        pieceIds.forEach(pieceId -> Assert.notNull(pieceId, "There cannot be a null value in the collection."));
        pieces.addAll(pieceIds);
    }

    /**
     * Get the collection of pieces ids. The collection is unmodifiable.
     * @return the collection of pieces ids.
     */
    public Set<Long> getPieceIds() {
        return Collections.unmodifiableSet(pieces);
    }

}
