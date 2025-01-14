package com.wooyeon.yeon.chat.controller;

import com.wooyeon.yeon.chat.dto.RoomDto;
import com.wooyeon.yeon.chat.service.RoomService;
import com.wooyeon.yeon.exception.WooyeonException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/list")
    public RoomDto.RoomResponse findMatchRoomList() throws WooyeonException {
        return roomService.matchRoomList();
    }

    @GetMapping("/search/list")
    public List<RoomDto.SearchRoomResponse> searchMatchRoomList(String searchWord) {
        return roomService.searchMatchRoomList(searchWord);
    }
}
