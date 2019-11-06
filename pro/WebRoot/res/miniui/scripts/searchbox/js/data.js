
//1）通过表格生成字段数组
var fields = [
    { name: '字符串', field: 'name', type: 'string', table: 'a', fastQueryId: "a", fastQuery: true, queryArgs: true, defaultVal: "", notNull: false, defOperator: "不等于" },
    { name: '数字', field: 'age', type: 'number', table: 'b', fastQueryId: "a", fastQuery: true, queryArgs: true, defaultVal: "", notNull: true, defOperator: "大于" },
    { name: '日期', field: 'birthday', type: 'date', table: 'c', fastQueryId: "a", fastQuery: true, queryArgs: true, defaultVal: "", notNull: false, defOperator: "介于" },
    { name: '日期时间', field: 'createtime', type: 'datetime', table: 'd', fastQueryId: "a", fastQuery: true, queryArgs: true, defaultVal: "", notNull: false, defOperator: "不介于" },
    { name: '选择', field: 'country', type: 'select', table: 'e', fastQueryId: "a", fastQuery: true, queryArgs: true, defaultVal: '[{ "id": "usa", "text": "美国" },{ "id": "cn", "text": "中国" }]', notNull: true, defOperator: "等于" },
    { name: '快速查询测试ddd', field: 'testfield', type: 'string', table: 'a', fastQueryId: "tid", fastQuery: true, queryArgs: true, defaultVal: "测试", notNull: false, defOperator: "等于" },
    
];

//2）通过“增加”生成查询条件
var data = [
//    { logic: "or", operator: "等于", value: "1", name: '字符串', field: 'name', type: 'string', table: 'a', fastQuery: true, queryArgs: true, defaultVal: "", notNull: true, defOperator: "等于" },
//    { logic: "and", operator: "补等于", value: "1", name: '数字', field: 'age', type: 'number', table: 'b', fastQuery: true, queryArgs: true, defaultVal: "", notNull: true, defOperator: "大于" },
//    { logic: "or", operator: "介于", value: "1", value2: "2", name: '日期', field: 'birthday', type: 'date', table: 'c', fastQuery: true, queryArgs: true, defaultVal: "", notNull: true, defOperator: "介于" },
//    { logic: "and", operator: "不介于", value: "3", value2: "4", name: '日期小时', field: 'createtime', type: 'datetime', table: 'd', fastQuery: true, queryArgs: true, defaultVal: "", notNull: true, defOperator: "介于" },
//    { logic: "or", operator: "包含", value: "1", name: '选择', field: 'country', type: 'select', table: 'e', fastQuery: true, queryArgs: true, defaultVal: '[{ "id": "usa", "text": "美国" },{ "id": "cn", "text": "中国" }]', notNull: true, defOperator: "等于" }
//
]