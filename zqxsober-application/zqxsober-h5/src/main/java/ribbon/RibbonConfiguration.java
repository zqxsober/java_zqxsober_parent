package ribbon;

import com.netflix.loadbalancer.IRule;
import com.zqxosber.h5.config.rule.EurekaRibbonRule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Author: zqxsober
 * @Description: RibbonConfiguration 类
 * @Date: 2022-07-25 17:17
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    @Profile("native")
    @ConditionalOnMissingBean(IRule.class)
    public IRule ribbonRule() {
        return new EurekaRibbonRule();
    }
}
