package phongvan.javaspringbootbackend.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import phongvan.javaspringbootbackend.entity.Role;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupRoleRequest {
    Integer groupId;
    Integer roleId;
}
