package com.github.bitcoin.services.impl;

import com.github.bitcoin.services.IBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlockService implements IBlockService {
}
