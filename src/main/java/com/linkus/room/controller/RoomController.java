package com.linkus.room.controller;

import com.linkus.common.SessionConst;
import com.linkus.member.dto.MemberDto;
import com.linkus.room.dto.AddRoomDto;
import com.linkus.room.dto.EditRoomDto;
import com.linkus.room.dto.RoomDto;
import com.linkus.room.service.RoomService;
import com.linkus.room.entity.Room;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rooms")
@Slf4j
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public String rooms(Model model) {
        List<Room> rooms = roomService.findRooms();

        List<RoomDto> dtos = new ArrayList<>();
        for (Room room : rooms) {
            RoomDto dto = new RoomDto();
            dto.setId(room.getId());
            dto.setRoomName(room.getRoomName());
            dto.setMemberNum(room.getMemberNum());

            dtos.add(dto);
        }

        model.addAttribute("rooms", dtos);
        return "rooms/rooms";
    }

    @GetMapping("/{roomId}")
    public String room(@PathVariable("roomId") Long roomId, Model model) {
        Room room = roomService.findRoom(roomId);

        RoomDto dto = new RoomDto();
        dto.setId(room.getId());
        dto.setRoomName(room.getRoomName());
        dto.setMemberNum(room.getMemberNum());

        model.addAttribute("room", dto);
        return "rooms/room";
    }

    @GetMapping("/add")
    public String addRoomForm(@ModelAttribute("room") AddRoomDto dto) {
        return "rooms/addRoomForm";
    }

    @PostMapping("/add")
    public String addRoom(@Valid @ModelAttribute("room") AddRoomDto dto,
                          BindingResult bindingResult,
                          HttpServletRequest request,
                          RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return "rooms/addRoomForm";
        }

        roomService.makeRoom(dto);

        HttpSession session = request.getSession();
        MemberDto memberDto = (MemberDto) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Room room = roomService.findRoom(dto.getRoomName());

        redirectAttributes.addAttribute("roomId", room.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/rooms/{roomId}";
    }

    @GetMapping("/{roomId}/edit")
    public String editRoomForm(@PathVariable("roomId") Long roomId, Model model) {
        Room room =  roomService.findRoom(roomId);

        EditRoomDto dto = new EditRoomDto();
        dto.setId(room.getId());
        dto.setRoomName(room.getRoomName());

        model.addAttribute("room", dto);
        return "rooms/editRoomForm";
    }

    @PostMapping("/{roomId}/edit")
    public String editRoom(@PathVariable("roomId") Long roomId,
                           @Valid @ModelAttribute("room") RoomDto dto,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "rooms/editRoomForm";
        }

        roomService.updateRoom(roomId, dto.getRoomName());

        return "redirect:/rooms/{roomId}";
    }

    @GetMapping("/{roomId}/join")
    public String joinConference(@PathVariable("roomId") Long roomId, Model model) {

        // TODO 화상회의 참여 개발

        return "rooms/conference";
    }

}
