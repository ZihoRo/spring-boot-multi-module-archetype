#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.user.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ${userName} on ${today}.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoQuery {
    private Long id;
    private String name;
    private Integer age;
}
