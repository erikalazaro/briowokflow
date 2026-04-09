package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Footer", title = "Footer.")
@Data
@Builder
@NoArgsConstructor
public class Footer implements Serializable{

}
