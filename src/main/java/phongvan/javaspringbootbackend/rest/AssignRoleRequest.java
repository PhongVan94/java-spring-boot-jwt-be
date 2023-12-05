package phongvan.javaspringbootbackend.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignRoleRequest {
        String groupId;
        GroupRoleRequest[] groupRoles;
}
