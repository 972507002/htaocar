/**
 * Created by Administrator on 2017/12/19.
 */
var provinces = ['请选择省份','北京市','上海市','天津市','重庆市','河北省','山西省','内蒙古省','辽宁省','吉林省','黑龙江省','江苏省','浙江省','安徽省','福建省','江西省','山东省','河南省','湖北省','湖南省','广东省','广西省','海南省','四川省','贵州省','云南省','西藏省','陕西省','甘肃省','宁夏省','青海省','新疆省','香港','澳门','台湾'];
var citys = [['请选择城市'],
    ["东城区", "西城区", "崇文区", "宣武区", "朝阳区", "丰台区", "石景山区", "海淀区", "门头沟区", "房山区", "通州区", "顺义区", "昌平区", "大兴区", "怀柔区", "平谷区", "密云县", "延庆县"],
    ["黄浦区", "卢湾区", "徐汇区", "长宁区", "静安区", "普陀区", "虹口区", "杨浦区", "闵行区", "宝山区", "嘉定区", "浦东新区", "金山区", "松江区", "青浦区", "南汇区", "奉贤区", "崇明县"],
    ["和平区", "河东区", "河西区", "南开区", "河北区", "红桥区", "塘沽区", "汉沽区", "大港区", "东丽区", "西青区", "津南区", "北辰区", "武清区", "宝坻区", "宁河县", "静海县", "蓟县"],
    ["万州区", "涪陵区", "渝中区", "大渡口区", "江北区", "沙坪坝区", "九龙坡区", "南岸区", "北碚区", "万盛区", "双桥区", "渝北区", "巴南区", "黔江区", "长寿区", "綦江县", "潼南县", "铜梁县", "大足县", "荣昌县", "璧山县", "梁平县", "城口县", "丰都县", "垫江县", "武隆县", "忠县", "开县", "云阳县", "奉节县", "巫山县", "巫溪县", "石柱土家族自治县", "秀山土家族苗族自治县", "酉阳土家族苗族自治县", "彭水苗族土家族自治县", "江津市", "合川市", "永川市", "南川市"],
    ["石家庄市","张家口市","承德市","秦皇岛市","唐山市","廊坊市","保定市","衡水市","沧州市","邢台市","邯郸市"],
    ["太原市","朔州市","大同市","阳泉市","长治市","晋城市","忻州市","晋中市","临汾市","吕梁市","运城市"],
    ["呼和浩特市","包头市","乌海市","赤峰市","通辽市","呼伦贝尔市","鄂尔多斯市","乌兰察布市","巴彦淖尔市","兴安盟","锡林郭勒盟","阿拉善盟"],
    ["沈阳市","朝阳市","阜新市","铁岭市","抚顺市","本溪市","辽阳市","鞍山市","丹东市","大连市","营口市","盘锦市","锦州市","葫芦岛市"],
    ["长春市","白城市","松原市","吉林市","四平市","辽源市","通化市","白山市","延边州"],
    ["哈尔滨市","齐齐哈尔市","七台河市","黑河市","大庆市","鹤岗市","伊春市","佳木斯市","双鸭山市","鸡西市","牡丹江市","绥化市","大兴安岭地区"],
    ["南京市","徐州市","连云港市","宿迁市","淮安市","盐城市","扬州市","泰州市","南通市","镇江市","常州市","无锡市","苏州市"],
    ["杭州市","湖州市","嘉兴市","舟山市","宁波市","绍兴市","衢州市","金华市","台州市","温州市","丽水市"],
    ["合肥市","宿州市","淮北市","亳州市","阜阳市","蚌埠市","淮南市","滁州市","马鞍山市","芜湖市","铜陵市","安庆市","黄山市","六安市","巢湖市","池州市","宣城市"],
    ["福州市","南平市","莆田市","三明市","泉州市","厦门市","漳州市","龙岩市","宁德市"],
    ["南昌市","九江市","景德镇市","鹰潭市","新余市","萍乡市","赣州市","上饶市","抚州市","宜春市","吉安市"],
    ["济南市","青岛市","聊城市","德州市","东营市","淄博市","潍坊市","烟台市","威海市","日照市","临沂市","枣庄市","济宁市","泰安市","莱芜市","滨州市","菏泽市"],
    ["郑州市","开封市","三门峡市","洛阳市","焦作市","新乡市","鹤壁市","安阳市","濮阳市","商丘市","许昌市","漯河市","平顶山市","南阳市","信阳市","周口市","驻马店市","济源市"],
    ["武汉市","十堰市","襄樊市","荆门市","孝感市","黄冈市","鄂州市","黄石市","咸宁市","荆州市","宜昌市","随州市","省直辖县级行政单位","恩施州"],
    ["长沙市","张家界市","常德市","益阳市","岳阳市","株洲市","湘潭市","衡阳市","郴州市","永州市","邵阳市","怀化市","娄底市","湘西州"],
    ["广州市","深圳市","清远市","韶关市","河源市","梅州市","潮州市","汕头市","揭阳市","汕尾市","惠州市","东莞市","珠海市","中山市","江门市","佛山市","肇庆市","云浮市","阳江市","茂名市","湛江市"],
    ["南宁市","桂林市","柳州市","梧州市","贵港市","玉林市","钦州市","北海市","防城港市","崇左市","百色市","河池市","来宾市","贺州市"],
    ["海口市","三亚市","省直辖县级行政单位"],
    ["成都市","广元市","绵阳市","德阳市","南充市","广安市","遂宁市","内江市","乐山市","自贡市","泸州市","宜宾市","攀枝花市","巴中市","达州市","资阳市","眉山市","雅安市","阿坝州","甘孜州","凉山州"],
    ["贵阳市","六盘水市","遵义市","安顺市","毕节地区","铜仁地区","黔东南州","黔南州","黔西南州"],
    ["昆明市","曲靖市","玉溪市","保山市","昭通市","丽江市","思茅市","临沧市","德宏州","怒江州","迪庆州","大理州","楚雄州","红河州","文山州","西双版纳州"],
    ["拉萨市","那曲地区","昌都地区","林芝地区","山南地区","日喀则地区","阿里地区"],
    ["西安市","延安市","铜川市","渭南市","咸阳市","宝鸡市","汉中市","榆林市","安康市","商洛市"],
    ["兰州市","嘉峪关市","白银市","天水市","武威市","酒泉市","张掖市","庆阳市","平凉市","定西市","陇南市","临夏州","甘南州"],
    ["西宁市","海东地区","海北州","海南州","黄南州","果洛州","玉树州","海西州"],
    ["银川市","石嘴山市","吴忠市","固原市","中卫市"],
    ["乌鲁木齐市","克拉玛依市","自治区直辖县级行政单位","喀什地区","阿克苏地区","和田地区","吐鲁番地区","哈密地区","克孜勒苏柯州","博尔塔拉州","昌吉州","巴音郭楞州","伊犁州","塔城地区","阿勒泰地区"],
    ["香港"],
    ["澳门"],
    ["台北市","高雄市","台中市","花莲市","基隆市","嘉义市","金门市","连江市","苗栗市","南投市","澎湖市","屏东市","台东市","台南市","桃园市","新竹市","宜兰市","云林市","彰化市"]];


var brands = ['请选择品牌','奥迪','大众','奔驰','宝马','别克','雪佛兰','福特','丰田','东风悦达','宾利','保时捷','吉利','东南汽车','奇瑞QQ','现代','比亚迪','本田','日产'];
var seriess= [['请选择车系'],
    ["奥迪A4","奥迪A5","奥迪A6","奥迪A8L","奥迪Q3","奥迪Q7","奥迪S5","奥迪A8","奥迪SQ5","奥迪TT","奥迪RS5","奥迪R8","奥迪RS7"],
    ["大众cc","大众Eos","辉腾","甲壳虫","迈腾","大众up！","帕萨特","宝来","朗逸","速腾","尚酷","途观","高尔","凌度"],
    ["奔驰GLK级","奔驰GL级","奔驰GL级","奔驰L级","奔驰GLK级","奔驰M级","奔驰S级","奔驰SL级","奔驰C级","奔驰E级","奔驰B级","奔驰A级","奔驰R级","奔驰GLA级"],
    ["宝马1系","宝马3系","宝马5系","宝马7系","宝马X3","宝马X5","宝马X6","宝马Z4","宝马6系","宝马X1","宝马X4","宝马i3","宝马i8","宝马X5 M"],
    ["别克GL8","君越","凯越","凯越HRV","英朗","君威","荣御","昂科拉" , "威郎","别克GL6","新世纪","阅郎"],
    ["景程","月风","乐驰","科帕奇","科迈罗","乐聘","创酷","赛欧" , "爱唯欧","科沃兹"],
    ["福特S-MAX","蒙迪欧","翼虎","锐界","福克斯","野马","金牛座 ","探险者" , "嘉年华","翼搏"],
    ["汉兰达","红杉","雅力士","花冠","卡罗拉","威驰","普拉多 ","艾尔法" , "锐志","坦途"],
    ["御风","俊风","奥丁","帅克","御风EV","御轩","虎视 ","东风皮卡" , "锐奇皮卡"],
    ["欧陆","慕尚","飞驰","添越","雅致","野马"],
    ["保时捷911","保时捷Boxster","保时捷Cayman","保时捷Carrera","保时捷918","保时捷Macan","保时捷718"],
    ["豪情","美人豹","金刚","金鹰","英伦TX4","金刚2代","吉利SX7","豪情SUV"],
    ["凌帅","希旺","V6凌士","东南DX7","东南DX3","V3凌悦"],
    ["奇瑞A1","奇瑞A5","奇瑞QQ6","奇瑞QQ3","旗云","瑞虎"],
    ["维拉克斯","雅科仕","雅尊","悦动","雅绅特","郎动","瑞纳","酷派"],
    ["比亚迪F6","比亚迪S8","比亚迪G3","比亚迪M6","比亚迪L3","比亚迪e6"],
    ["飞度","思迪","歌诗图","本田CR-Z","凌派","本田XR-V"],
    ["帕拉丁","风雅","逍客","楼兰","日产370Z","奇骏"]

];

function init(){
    var province = document.getElementById('param_province');
    //给选择框一个高度，可直接写进数据，不然要先创建dom元素option再录值
    province.length=provinces.length;
    for(var i=0;i<provinces.length;i++){
        province.options[i].text=provinces[i];
        province.options[i].value=provinces[i];
    }

    var brand = document.getElementById('param_brand');
    //给选择框一个高度，可直接写进数据，不然要先创建dom元素option再录值
    brand.length=brands.length;
    for(var i=0;i<brands.length;i++){
        brand.options[i].text=brands[i];
        brand.options[i].value=brands[i];
    }



}

function provincechange(n){
    var city = document.getElementById('param_city');
    var citystemp=citys[n];
    city.length=citystemp.length;
    for(var i=0;i<citystemp.length;i++){
        city.options[i].text=citystemp[i];
        city.options[i].value=citystemp[i];
    }
    city.options[0].selected=true;
}

function brandchange(n){
    var series = document.getElementById('param_series');
    var seriestemp=seriess[n];
    series.length=seriestemp.length;
    for(var i=0;i<seriestemp.length;i++){
        series.options[i].text=seriestemp[i];
        series.options[i].value=seriestemp[i];
    }
    series.options[0].selected=true;
}
