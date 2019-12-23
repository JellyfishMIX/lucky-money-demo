package com.imooc.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class LuckymoneyController {
    @Autowired
    private LuckymoneyRepository luckymoneyRepository;

    /**
     * 获取红包列表
     * @return
     */
    @GetMapping("getluckymoneylist")
    public List<Luckymoney> getLuckymoneyList() {
        return luckymoneyRepository.findAll();
    }

    /**
     * 创建红包
     * @param producer
     * @param money
     * @return
     */
    @PostMapping("/createluckymoney")
    public Luckymoney createMoney(@RequestParam("producer") String producer, @RequestParam("money")BigDecimal money) {
        Luckymoney luckymoney = new Luckymoney();
        luckymoney.setProducer(producer);
        luckymoney.setMoney(money);
        return luckymoneyRepository.save(luckymoney);
    }

    /**
     * 通过Id查询某个红包
     * @param id
     * @return
     */
    @GetMapping("/getluckymoneybyid")
    public Luckymoney getLuckymoneyById(@RequestParam("id") Integer id) {
        return luckymoneyRepository.findById(id).orElse(null);
    }

    /**
     * 更新红包（领红包）
     * @param id
     * @param consumer
     * @return
     */
    @PutMapping("/updateluckymoney")
    public Luckymoney updateLuckymoney(@RequestParam("id") Integer id, @RequestParam("consumer") String consumer) {
        Optional<Luckymoney> optional = luckymoneyRepository.findById(id);
        if (optional.isPresent()) {
            Luckymoney luckymoney = optional.get();
            luckymoney.setConsumer(consumer);
            return luckymoneyRepository.save(luckymoney);
        } else {
            return null;
        }
    }
}
