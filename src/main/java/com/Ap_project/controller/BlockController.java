package com.Ap_project.controller;

import com.Ap_project.DAO.BlockDAO;
import com.Ap_project.model.Block;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

public class BlockController {
    private final BlockDAO blockDAO;

    public BlockController() throws SQLException {
        this.blockDAO = new BlockDAO();
    }
    public void creatBlock(String blocker, String blocked) throws SQLException {
        Block block = new Block(blocker , blocked);
        blockDAO.saveBlock(block);
    }

    public void deleteBlock(Block block) throws SQLException {
        blockDAO.deleteBlock(block);
    }

    public void deleteAll() throws SQLException {
        blockDAO.deleteAll();
    }

    public String getBlocks(String userId) throws SQLException, JsonProcessingException {
        List<Block> blocks = blockDAO.getBlocks(userId);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(blocks);
    }

    public String getBlockers(String userId) throws SQLException, JsonProcessingException {
        List<Block> blocks = blockDAO.getBlockers(userId);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(blocks);
    }

    public String getAll() throws SQLException, JsonProcessingException {
        List<Block> blocks = blockDAO.getAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(blocks);
    }

    public boolean isBlocking(String blockerId, String blockedId) throws SQLException {
        return blockDAO.isBlocking(blockerId, blockedId);
    }
}
