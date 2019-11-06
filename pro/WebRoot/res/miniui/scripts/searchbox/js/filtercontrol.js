
//条件数组。提供给“高级查询”的条件下拉框使用
var Operators = [
    { text: "等于" },
    { text: "不等于" },
    { text: "大于"},
    { text: "小于"},    
    { text: "大于等于"},
    { text: "包含"},
    { text: "左包含"},
    { text: "右包含"},
    { text: "不包含"},
    { text: "集合" }
//    ,
//    { text: "介于" },
//    { text: "不介于"}
];

/**
 * 去掉没有用的属性
 * kingschan 
 * 2014-6-10
 * @param data
 */
function delproperties(data){
	var regx =/name|fastQueryId|queryArgs|defaultVal|notNull|fastQuery|text/;
	for(var i=0;i<data.length;i++){
		var o = data[i];
	    for( var properties in o){
	    	if(properties.match(regx)){
	    		delete o[properties];
	    	}
	    }
	}
}




//“高级查询”组件
var FilterControl = function () {
    this.create();
}
FilterControl.prototype = {

    //获取查询条件数组
    getData: function () {
        var data = this.grid.getData();        
        for (var i = 0, l = data.length; i < l; i++) {
            var o = data[i];
            var field = this.getFieldByValue(o.field);           
            $.extend(o, field);//合并
            o.fastQuery = false;//高级查询添加的记录，fastQuery=false
            
        }

        data = mini.clone(data);
        //////////////////////////////////////////////
        delproperties(data);
        ////////////////去掉没有用的属性kingschan 2014-6-10////////////////////////////
        return data;
    },
    //设置查询条件数组
    setData: function (data) {
        //清除fastQuery为true的字段
        this.data = [];
        for (var i = 0, l = data.length; i < l; i++) {
            var o = data[i];
            if (o.fastQuery) continue;
            this.data.push(o);
        }
        this.grid.setData(this.data);
    },
    //设置“列名”下拉框数据。一般从表格列配置直接生成而来。
    setFields: function (fields) {
        //清除queryArgs为false的字段
        this.fields = [];
        for (var i = 0, l = fields.length; i < l; i++) {
            var o = fields[i];
            if (!o.queryArgs) continue;
            this.fields.push(o);
        }

        FillSelect(this.$fields, this.fields, "field", "name");

        this.initValue();
    },
    //创建“查询条件”表格
    createGrid: function () {
        var that = this;
        this.columns = [
            { field: "logic", header: "关系", width: 40,
                renderer: function (e) {
                    var value = e.value, record = e.record;
                    if (value == "(" || value == ")") return value;
                    var o = that.getLogicByValue(value);
                    return o ? o.text : "";
                }
            },
            { field: "field", header: "字段", width: 120,
                renderer: function (e) {
                    var value = e.value, record = e.record;
                    if (value == "(" || value == ")") return value;
                    //if (record.text) return record.text;
                    var o = that.getFieldByValue(value);
                    return o ? o.name : "";
                }
            },
            { field: "operator", header: "条件", width: 40,
                renderer: function (e) {
                    return e.value;
                    //                    var value = e.value, record = e.record;
                    //                    if (value == "(" || value == ")") return "";
                    //                    var o = that.getOperatorByValue(value);
                    //                    return o ? o.text : "";
                }
            },
            { field: "value", header: "值", width: 150,
                renderer: function (e) {
                    var value = e.value, record = e.record;
                    if (record.text) return record.text;
                    if (value == "(" || value == ")") return "";
                    return value;
                }
            },
            { header: "", cls: '', width: 50,
                renderer: function (e) {
                    var value = e.value, record = e.record;
                    var column = e.column, index = e.rowIndex;
                    return '<input index="' + index + '" class="fc-btn" type="button" value="删除"/>';
                }
            }
        ];
        this.grid = new mini.DataGrid();
        this.grid.set({
            render: this.$content,
            cls: "fc-table",
            showPager: false,
            columns: this.columns
        });
    },
    //创建控件内部细节控件
    create: function () {
        var that = this;
        this.el = document.createElement("div");
        var html = '<div><select class="fc-logic" name="logic"></select>'
                        + '<input class="fc-before" type="button" name="before" value="("/>'
                        + '<input class="fc-after" type="button" name="after" value=")"/>'
                        + '<select class="fc-fields" name="fields"></select>'
                        + '<select class="fc-operator" name="operator"></select>'
                        + '<span name="value"></span>'
                        + '<input class="fc-add" type="button" name="add" value="添加"/>'
                        + '<input class="fc-paste fc-btn" type="button" name="paste" value="粘贴"/>'
                        + '</div>';

        html += '<div name="content"></div>';

        this.el.innerHTML = html;

        this.$logic = $("[name=logic]", this.el)[0];                //“逻辑”下拉框
        this.$before = $("[name=before]", this.el)[0];              //“（”按钮
        this.$after = $("[name=after]", this.el)[0];                //“）”按钮
        this.$fields = $("[name=fields]", this.el)[0];              //“列名”下拉框
        this.$operator = $("[name=operator]", this.el)[0];          //“关系”下拉框
        this.$value = $("[name=value]", this.el)[0];                //“值”输入框
        this.$add = $("[name=add]", this.el)[0];                    //“添加”按钮
        this.$paste = $("[name=paste]", this.el)[0];                //“粘贴”按钮
        this.$content = $("[name=content]", this.el)[0];            //放置“查询条件表格”的元素

        this.logics = [{ text: "并且", value: "and" }, { text: "或者", value: "or"}];
        this.operators = Operators;


        FillSelect(this.$logic, this.logics, "value", "text");          //将逻辑数据填充到下拉框
        FillSelect(this.$operator, this.operators, "text", "text");     //将关系数据填充到下拉框


        this.createGrid();
        this.fields = [];
        this.data = [];

        ///////////////////////////////////////////////////////
        var that = this;

        //“粘贴”按钮处理
        that.$paste.onclick = function () {
            cellkeydown(that);
        }

        //“添加”按钮处理
        that.$add.onclick = function () {
            var editor = that.getValueEditor();
            editor.validate();
            if (!editor.isValid()) return;

            var o = {
                logic: that.$logic.value,
                field: that.$fields.value,
                operator: that.$operator.value,
                value: that.getValue()
            };

            var fieldObj = that.getFieldByValue(o.field);

            if (fieldObj.type == "select") {
                var data = mini.decode(fieldObj.defaultVal);
                o.text = GetTextFromSelect(o.value, data || []);
            }
            if (fieldObj.type == "date") o.text = mini.formatDate(o.value, fieldObj.format || "yyyy-MM-dd");
            if (fieldObj.type == "datetime") o.text = mini.formatDate(o.value, fieldObj.format || "yyyy-MM-dd HH:mm:ss");

            that.data.add(o);
            that.setData(that.data);

            setTimeout(function () {
                editor.setValue("");
            }, 100);

            //alert(editor.getValue());
        }

        //“删除”按钮处理
        $(that.$content).on("click", ".fc-btn", function (e) {
            var index = parseInt($(e.target).attr("index"));

            that.data.removeAt(index);
            that.setData(that.data);
        });

        //“（”按钮处理
        that.$before.onclick = function () {
            var o = {
                logic: that.$logic.value,
                field: "("
            };
            that.data.add(o);
            that.setData(that.data);
        }

        //“）”按钮处理
        that.$after.onclick = function () {
            var o = {
                logic: that.$logic.value,
                field: ")"
            };
            that.data.add(o);
            that.setData(that.data);
        }

        //“列名”下拉框选择处理
        that.$fields.onchange = function () {
            that.initValue();

        }

        //“关系”下拉框选择处理
        that.$operator.onchange = function () {
            pasteDisplay();
        }

        function pasteDisplay() {
            //关系为“集合”时，显示
            that.$paste.style.display = that.$operator.value == "集合" ? "" : "none";
        }
        pasteDisplay();

        ////////////////////////////////////////////////
        that.value_string = new mini.TextBox().set({ width: 150, required: true });
        //that.value_boolean = new mini.CheckBox();
        that.value_number = new mini.Spinner().set({ width: 150, required: true, allowNull: true, maxValue: 100000000000, minValue: -100000000000, decimalPlaces: 2 });
        that.value_date = new mini.DatePicker().set({ width: 150, required: true });
        that.value_datetime = new mini.DatePicker().set({ width: 150, required: true, showTime: true, format: "yyyy-MM-dd HH:mm:ss", timeFormat: "H:mm:ss" });
        that.value_select = new mini.ComboBox().set({ width: 150, valueField: "id", textField: "text", required: true });

        that.value_string.render(that.$value);
        that.value_number.render(that.$value);
        that.value_date.render(that.$value);
        that.value_datetime.render(that.$value);
        that.value_select.render(that.$value);

        //初始化值（处理不同类型编辑器的同步问题）
        that.initValue = function (value) {
            that.value_string.hide();
            //that.value_boolean.hide();
            that.value_number.hide();
            that.value_date.hide();
            that.value_datetime.hide();
            that.value_select.hide();


            var o = that.getFieldByValue(that.$fields.value);
            var editor = that.value_string
            if (o) {
                if (that["value_" + o.type]) editor = that["value_" + o.type];
                if (o.type == "select") {
                    try {
                        var data = mini.decode(o.defaultVal);
                        editor.setData(data);
                    } catch (ex) {
                    }
                }
            }
            editor.show();

            if (o) {
                that.$operator.value = o.defOperator;   //切换field时，选中默认的operator
                if (!that.$operator.value) {
                    that.$operator.value = "等于";
                }
            }

            if (value) editor.setValue(value);
        }
        that.initValue();


    },
    render: function (el) {
        el.appendChild(this.el);
    },
    //根据类型获取当前编辑器
    getValueEditor: function () {
        var that = this;
        var editor;
        var o = that.getFieldByValue(that.$fields.value);
        if (o) {
            if (that["value_" + o.type]) editor = that["value_" + o.type];
        }
        if (!editor) editor = that.value_string;
        return editor;
    },
    //获取当前编辑器的值
    getValue: function () {
        var that = this, value = that.value_string.getValue();
        //if (that.value_boolean.isDisplay()) value = that.value_boolean.getValue();
        if (that.value_number.isDisplay()) value = that.value_number.getValue();
        if (that.value_date.isDisplay()) value = that.value_date.getValue();
        if (that.value_datetime.isDisplay()) value = that.value_datetime.getValue();
        if (that.value_select.isDisplay()) value = that.value_select.getValue();
        return value;
    },
    //根据值获取“逻辑”对象，以便获取更多信息
    getLogicByValue: function (value) {
        for (var i = 0, l = this.logics.length; i < l; i++) {
            var o = this.logics[i];
            if (o.value == value) return o;
        }
        return null;
    },
    //已经无用，现在“关系”直接是中文。不要删除
    getOperatorByValue: function (value) {
        return value;
        //        for (var i = 0, l = this.operators.length; i < l; i++) {
        //            var o = this.operators[i];
        //            if (o.value == value) return o;
        //        }
        //        return null;
    },
    //根据field找到对象，以便得到更多信息
    getFieldByValue: function (value) {
        for (var i = 0, l = this.fields.length; i < l; i++) {
            var o = this.fields[i];
            if (o.field == value) return o;
        }
        return null;
    }

};


//“快速查询”组件
var SearchControl = function () {
    this.create();
}
SearchControl.prototype = {

    //根据数据生成快速查询表单
    createForm: function () {
        var fields = this.fields, el = this.el;

        //总共支持的编辑器类型
        var editors = {
            "string": mini.TextBox,
            "number": mini.Spinner,
            "date": mini.DatePicker,
            "datetime": mini.DatePicker,
            "select": mini.ComboBox
        };

        //创建编辑器控件，以及初始化
        function createEditor(o) {
            var clazz = editors[o.type];
            if (!clazz) return;

            var editor = new clazz();
            editor.set({
                style: "float:left;margin-right:10px;",
                labelField: true,
                label: o.name,
                labelStyle: o.notNull ? "color:red" : "",
                name: o.field,
                allowNull: true,
                value: "",
                //required: o.notNull,
                decimalPlaces: 2,
                allowLimitValue: false,
                showNullItem: true
            });

            if (o.type == "select") {
                try {
                    
                    var data = mini.decode(o.defaultVal);
                    editor.setData(data);
                } catch (ex) {
                }
            }
            if (o.type == "datetime") {
                editor.set({
                    format: "yyyy-MM-dd H:mm:ss",
                    timeFormat: "H:mm:ss",
                    showTime: true
                });
            }
            return editor;
        }

        function createField(o) {

            if (o.defOperator == "介于" || o.defOperator == "不介于") {

                var editor = createEditor(o);
                var editor2 = createEditor(o);

                editor2.set({ "label": o.defOperator, name: o.field + "2" });

                //$(el).append("<br/>");
                var div = $('<div style="clear:both;"></div>').appendTo(el)[0];
                editor.render(div);
                editor2.render(div);
                $('<div style="clear:both;"></div>').appendTo(el);

            } else {
                var editor = createEditor(o);
                editor.render(el);
            }
        }

        for (var i = 0, l = fields.length; i < l; i++) {
            var o = fields[i];
            createField(o);
        }
        $('<div style="clear:both;"></div>').appendTo(el);

    },
    create: function () {
        var that = this;
        this.el = document.createElement("div");

    },
    render: function (el) {
        el.appendChild(this.el);
    },
    setFields: function (fields) {
        //清除掉fastQuery为false的字段
        this.fields = [];
        for (var i = 0, l = fields.length; i < l; i++) {
            var o = fields[i];
            if (!o.fastQuery) continue;
            this.fields.push(o);
        }

        this.createForm();
    },
    // 生成查询记录数组
    getData: function () {
        //判断表单是否有录入值，有则组织成一个数组记录返回
        var data = [];

        for (var i = 0, l = this.fields.length; i < l; i++) {
            var field = fields[i];
            var editor = mini.getbyName(field.field, this.el);
            var value = editor ? editor.getValue() : "";
            if (value !== "" && value !== null) {
                var record = { value: value };
                if (field.defOperator == "介于" || field.defOperator == "不介于") {
                    var editor2 = mini.getbyName(field.field + "2", this.el);
                    record.value2 = editor2 ? editor2.getValue() : "";
                    if (!record.value2) continue;
                }

                $.extend(record, field);
                data.push(record);

                record.logic = "and";
            }
        }

        data = mini.clone(data);
        //////////////kingschan修改2014-6-10///////////////////////////
        delproperties(data);
        ////////////////去掉没有用的属性kingschan 2014-6-10////////////////////////////
        return data;
    },
    setData: function (data) {

        var form = new mini.Form(this.el);
        form.clear();

        //去除fastQuery为false，以及logic为"or"
        for (var i = 0, l = data.length; i < l; i++) {
            var o = data[i];
            if (!o.fastQuery || o.logic != "and") continue;

            var editor = mini.getbyName(o.field, this.el);
            var editor2 = mini.getbyName(o.field + "2", this.el);
            if (editor) editor.setValue(o.value);
            if (editor2) editor2.setValue(o.value2);

        }

    }

};

//notNull=true时，高级或快速，必须有一个
function ValidFilter(data, fields) {

    function valid(field) {
        for (var i = 0, l = data.length; i < l; i++) {
            var o = data[i];
            if (o.field == field) return true;
        }
        return false;
    }

    var sb = [];
    for (var i = 0, l = fields.length; i < l; i++) {
        var o = fields[i];
        if(o.notNull == false) continue;
        if (!valid(o.field)) {
            sb.push(o.name);
        }
    }

    if (sb.length > 0) {
        var str = sb.join(' ');
        var content = '<div style="line-height:16px;text-align:left;padding-left:5px">【红色必填！！】<br/>存在如下必填选择为空：<br/>' + str + '</div>';
        mini.alert(content, "信息");

    } else {
        return true;
    }
}


//填充select控件
function FillSelect(el, data, valueField, textField) {
    if (!valueField) valueField = "value";
    if (!textField) textField = "text";
    el.length = 0;
    for (var i = 0, l = data.length; i < l; i++) {
        var o = data[i];

        var opt = new Option(o[textField], o[valueField]);
        if (o.notNull) {       
            opt.style.cssText = "color:#FF0000";
        }
        el.add(opt);
    }
}

// 从一个数组内，根据记录的value获取text文本
function GetTextFromSelect(value, data) {
    var values = value.split(",");
    var valueField = "id", textField = "text";
    var valueMaps = {};
       
    for (var i = 0, l = data.length; i < l; i++) {
        var o = data[i];
        valueMaps[o[valueField]] = o;
    }

    var texts = [];
    for (var i = 0, l = values.length; i < l; i++) {
        var id = values[i];
        var o = valueMaps[id];
        if (o) {
            var text = o[textField];
            if (text === null || text === undefined) {
                text = "";
            }
            texts.push(text);
        }
    }
    return texts.join(',');
}


//扩展js原生Array数组对象方法
$.extend(Array.prototype, {
    add: Array.prototype.enqueue = function (item) {
        this[this.length] = item;
        return this;
    },
    getRange: function (start, end) {
        var arr = [];
        for (var i = start; i <= end; i++) {
            var o = this[i];
            if (o) {
                arr[arr.length] = o;
            }
        }
        return arr;
    },
    addRange: function (array) {
        for (var i = 0, j = array.length; i < j; i++) this[this.length] = array[i];
        return this;
    },
    clear: function () {
        this.length = 0;
        return this;
    },
    clone: function () {
        if (this.length === 1) {
            return [this[0]];
        }
        else {
            return Array.apply(null, this);
        }
    },
    contains: function (item) {
        return (this.indexOf(item) >= 0);
    },
    indexOf: function (item, from) {
        var len = this.length;
        for (var i = (from < 0) ? Math.max(0, len + from) : from || 0; i < len; i++) {
            if (this[i] === item) return i;
        }
        return -1;
    },
    dequeue: function () {
        return this.shift();
    },
    insert: function (index, item) {
        this.splice(index, 0, item);
        return this;
    },
    insertRange: function (index, items) {
        for (var i = items.length - 1; i >= 0; i--) {
            var item = items[i];
            this.splice(index, 0, item);

        }
        return this;
    },
    remove: function (item) {
        var index = this.indexOf(item);
        if (index >= 0) {
            this.splice(index, 1);
        }
        return (index >= 0);
    },
    removeAt: function (index) {
        var ritem = this[index];
        this.splice(index, 1);
        return ritem;
    },
    removeRange: function (items) {
        items = items.clone();
        for (var i = 0, l = items.length; i < l; i++) {
            this.remove(items[i]);
        }
    }
});


////////////////////////////////////////////////////////////////////////////////////////////////////

//处理剪切板内容
function cellkeydown(control) {
    var strs = new Array();
    var str = getClipboard();
    var msg = "";
    strs = str.split("\r\n");
    if (strs != null && strs.length > 0) {
        if (strs.length > 101) {
            mini.alert('提示', "<b><font color='red'>请勿一次粘贴超过100个值！</font><b>", "error");
            return;
        }
        if (strs.length == 2 && str.split("\t").length > 1) {
            strs = str.split("\t");
            if (strs.length > 101) {
                mini.alert('提示', "<b><font color='red'>请勿一次粘贴超过100个值！</font><b>", "error");
                return;
            }
            for (i = 0; i < strs.length; i++) {
                if (msg == '') {
                    msg = strs[i].replace(/\t/g, "").trim();
                } else {
                    var tempStr = strs[i].replace(/\r\n/g, "").trim();
                    if (tempStr != '') {
                        msg += "," + tempStr;
                    }
                }
            }
        } else {
            for (i = 0; i < strs.length; i++) {
                if (msg == '') {
                    msg = strs[i].replace(/\r\n/g, "").trim();
                } else {
                    var tempStr = strs[i].replace(/\r\n/g, "").trim();
                    if (tempStr != '') {
                        msg += "," + tempStr;
                    }
                }

            }
        }
    } else {
        strs = str.split("\r\n");
    }
    control.initValue(msg);
}
//获取剪切板内容
function getClipboard() {
    if (window.clipboardData) {
        return (window.clipboardData.getData('Text'));
    }
    else if (window.netscape) {
        netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');
        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
        if (!clip) return;
        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
        if (!trans) return;
        trans.addDataFlavor('text/unicode');
        clip.getData(trans, clip.kGlobalClipboard);
        var str = new Object();
        var len = new Object();
        try {
            trans.getTransferData('text/unicode', str, len);
        } catch (error) {
            return null;
        }
        if (str) {
            if (Components.interfaces.nsISupportsWString) str = str.value.QueryInterface(Components.interfaces.nsISupportsWString);
            else if (Components.interfaces.nsISupportsString) str = str.value.QueryInterface(Components.interfaces.nsISupportsString);
            else str = null;
        }
        if (str) {
            return (str.data.substring(0, len.value / 2));
        }
    }
    return null;
} 