package phongvan.javaspringbootbackend.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import phongvan.javaspringbootbackend.entity.Group;
import phongvan.javaspringbootbackend.entity.Role;
import phongvan.javaspringbootbackend.repository.GroupRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupApiService {
    private final GroupRepository groupRepository;

    public Response getGroupMemberList(){
        try {
            List<Group> groups = groupRepository.findAll();
            if (groups.isEmpty()){
                return Response.builder()
                        .EC(-1)
                        .EM("NOT FOUND ANY GROUP")
                        .DT(null)
                        .build();
            }else {
                return Response.builder()
                        .EC(0)
                        .EM("GET GROUP SUCCESS")
                        .DT(groups)
                        .build();
            }
        } catch (Exception e) {
            return Response.builder()
                    .EC(-1)
                    .EM("SOMETHING WENT WRONG IN SERVER")
                    .DT(null)
                    .build();
        }
    }

    public Response createNewGroup(Collection<Group> groupList) {
        try {
            Collection<Group> groups = new ArrayList<>();
            for (Group group : groupList) {
                String groupName = group.getName();
                Optional<Group> groupExists = groupRepository.findByName(groupName);
                if (groupExists.isEmpty()) {
                    groups.add(group);
                }
            }

            if (groups.isEmpty()) {
                return Response.builder()
                        .EC(0)
                        .EM("NOTHING TO CREATE")
                        .DT(null)
                        .build();
            }
            groupRepository.saveAllAndFlush(groups);

            return Response.builder()
                    .EC(0)
                    .EM("CREATE GROUP SUCCESS: " + groups.size() + " GROUPS")
                    .DT(null)
                    .build();
        } catch (Exception e) {
            return Response.builder()
                    .EC(-1)
                    .EM("SOMETHING WENT WRONG IN SERVER")
                    .DT(null)
                    .build();
        }
    }
}
