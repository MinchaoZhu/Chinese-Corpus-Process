create table if not exists word( 
    word_id int not null auto_increment,
    word char(1) not null comment '汉字',
    oldword char(1) not null comment '繁体',
    strokes tinyint not null comment '笔画',
    pinyin varchar(8) not null comment '拼音',
    radicals char(1) comment '部首',
    explanation varchar(2000) not null comment '解释',
    more varchar(18000) comment '更多',
    index(word),
    primary key(word_id),
    index(pinyin),
    index(strokes),
    index(radicals)
)default charset=utf8 comment'汉字表';

create table if not exists ci( 
    ci_id int not null auto_increment,
    ci varchar(64) not null comment '词语',
    explanation varchar(2000) not null comment '解释',
    primary key(ci_id),
    index(ci)
)default charset=utf8 comment'词语表';

create table if not exists idiom(
    idiom_id int not null auto_increment,
    idiom varchar(32) not null comment '成语',
    derivation varchar(1000) comment '出处',
    explanation varchar(1000) comment '解释',
    example varchar(128) comment '例子',
    pinyin varchar(128) not null comment '拼音',
    first_pinyin varchar(10) not null comment '首字拼音',
    last_pinyin varchar(10) not null comment '尾字拼音',
    abbreviation varchar(32),comment '缩写',
    index(idiom),
    primary key(idiom_id),
    index(first_pinyin),
    index(last_pinyin)
)default charset=utf8 comment '成语表';

create table if not exists xiehouyu(
    xiehouyu_id int not null auto_increment,
    riddle varchar(64) not null comment '歇后语上句',
    answer varchar(64) not null comment '歇后语下句',
    index(riddle),
    index(answer),
    primary key(xiehouyu_id)
)default charset=utf8 comment '歇后语表';