package com.carara.notes.note;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class NoteDTO {

    private Long id;

    @Size(max = 255)
    private String title;

    private String text;

}
