package com.straight_ocean.coin;

import com.mojang.datafixers.types.templates.TypeTemplate;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.datafixers.types.Type;

// 2. 注册数据组件到玩家实体
public class CoinData extends Type<Coin> {
    @Override
    public TypeTemplate buildTemplate() {
        return null;
    }

    @Override
    public Codec<Coin> buildCodec() {
        return RecordCodecBuilder.create(instance -> instance.group(
            Codec.LONG.fieldOf("balance").forGetter(Coin::getBalance)
        ).apply(instance, Coin::new));
    }

    @Override
    public boolean equals(Object o, boolean ignoreRecursionPoints, boolean checkIndex) {
        return false;
    }
}
