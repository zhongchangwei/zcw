prompt PL/SQL Developer import file
prompt Created on 2018年11月4日 星期日 by Administrator
set feedback off
set define off
prompt Creating T_BASE_FIELD...
create table T_BASE_FIELD
(
  id            VARCHAR2(32) not null,
  tablename     VARCHAR2(50),
  field         VARCHAR2(50),
  tname         VARCHAR2(300),
  width         VARCHAR2(10),
  dateformat    VARCHAR2(20),
  datatype      VARCHAR2(20),
  ishidden      VARCHAR2(1),
  sort          VARCHAR2(10),
  create_date   DATE,
  create_userid VARCHAR2(32),
  remark        VARCHAR2(500),
  state         VARCHAR2(1),
  islist        VARCHAR2(1),
  isblank       VARCHAR2(10),
  pk_tablename  VARCHAR2(200),
  outputpath    VARCHAR2(200),
  packagename   VARCHAR2(50),
  beanname      VARCHAR2(20),
  modify_date   DATE,
  modify_userid VARCHAR2(32)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_BASE_FIELD
  is '页面标签生成，导出获取标题。。基础表';
comment on column T_BASE_FIELD.id
  is '主键id';
comment on column T_BASE_FIELD.tablename
  is '表名';
comment on column T_BASE_FIELD.field
  is '字段名';
comment on column T_BASE_FIELD.tname
  is '标题名称';
comment on column T_BASE_FIELD.width
  is '生成标签设置宽度（可10px或%20）';
comment on column T_BASE_FIELD.dateformat
  is '日期格式';
comment on column T_BASE_FIELD.datatype
  is '数据类型 (optiondata=下拉data类型  optionurl=下拉url类型  text=文本类型  date=时间类型  int=数字 )';
comment on column T_BASE_FIELD.ishidden
  is '是否隐藏 0=隐藏';
comment on column T_BASE_FIELD.sort
  is '排序';
comment on column T_BASE_FIELD.create_date
  is '创建时间';
comment on column T_BASE_FIELD.create_userid
  is '创建人id';
comment on column T_BASE_FIELD.remark
  is '备注';
comment on column T_BASE_FIELD.state
  is '是否有效：1：有效 0：删除';
comment on column T_BASE_FIELD.islist
  is '是否列表展示 1=表头 0字段';
comment on column T_BASE_FIELD.isblank
  is 'no:必填 yes:可空';
comment on column T_BASE_FIELD.pk_tablename
  is '外键表名称';
comment on column T_BASE_FIELD.outputpath
  is '输出路径';
comment on column T_BASE_FIELD.packagename
  is '二级路径——包所在追加路径';
comment on column T_BASE_FIELD.beanname
  is '实体名称';
comment on column T_BASE_FIELD.modify_date
  is '修改时间';
comment on column T_BASE_FIELD.modify_userid
  is '修改人';
alter table T_BASE_FIELD
  add constraint PK_T_BASE_FIELD primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_BASE_LOADREDIS...
create table T_BASE_LOADREDIS
(
  id            VARCHAR2(32) not null,
  table_name    VARCHAR2(50) not null,
  data_source   VARCHAR2(30),
  database_name VARCHAR2(50),
  remark        VARCHAR2(200),
  create_date   DATE,
  create_name   VARCHAR2(50),
  state         VARCHAR2(1) default '1',
  joint_symbol  VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_BASE_LOADREDIS
  is '缓存加载表';
comment on column T_BASE_LOADREDIS.id
  is '主键id';
comment on column T_BASE_LOADREDIS.table_name
  is '表名称';
comment on column T_BASE_LOADREDIS.data_source
  is '数据源名称（用户名称）';
comment on column T_BASE_LOADREDIS.database_name
  is '数据库名称';
comment on column T_BASE_LOADREDIS.remark
  is '备注';
comment on column T_BASE_LOADREDIS.create_date
  is '创建时间';
comment on column T_BASE_LOADREDIS.create_name
  is '创建人';
comment on column T_BASE_LOADREDIS.state
  is '是否有效：1：有效 0：无效';
comment on column T_BASE_LOADREDIS.joint_symbol
  is '连接符号';
alter table T_BASE_LOADREDIS
  add constraint PK_T_BASE_LOADREDIS primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_CODE_INFO...
create table T_CODE_INFO
(
  id               VARCHAR2(32) not null,
  pid              VARCHAR2(32),
  name_            VARCHAR2(200),
  isfolder         VARCHAR2(10),
  type             VARCHAR2(10),
  sort             INTEGER,
  url_             VARCHAR2(500),
  sys_flag         VARCHAR2(5),
  create_date      DATE default sysdate,
  create_user      VARCHAR2(100),
  create_user_name VARCHAR2(100),
  modify_date      DATE,
  modify_user      VARCHAR2(100),
  modify_user_name VARCHAR2(100),
  modify_type      VARCHAR2(20),
  state            VARCHAR2(5),
  remark           VARCHAR2(1000)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column T_CODE_INFO.id
  is '主键id';
comment on column T_CODE_INFO.pid
  is '父节点id';
comment on column T_CODE_INFO.name_
  is '代码名称';
comment on column T_CODE_INFO.isfolder
  is '是否目录(1：是 0：不是)';
comment on column T_CODE_INFO.type
  is '菜单、代码';
comment on column T_CODE_INFO.sort
  is '排序';
comment on column T_CODE_INFO.url_
  is '菜单URL';
comment on column T_CODE_INFO.sys_flag
  is '系统标记(1-系统属性)，标记为1为系统属性,不能删除';
comment on column T_CODE_INFO.create_date
  is '创建日期';
comment on column T_CODE_INFO.create_user
  is '创建人';
comment on column T_CODE_INFO.create_user_name
  is '创建人姓名';
comment on column T_CODE_INFO.modify_date
  is '修改日期';
comment on column T_CODE_INFO.modify_user
  is '修改人';
comment on column T_CODE_INFO.modify_user_name
  is '修改人姓名';
comment on column T_CODE_INFO.modify_type
  is '修改类型(create/update/delete)';
comment on column T_CODE_INFO.state
  is '状态(1-可用,0-不可用)';
comment on column T_CODE_INFO.remark
  is '备注';
alter table T_CODE_INFO
  add constraint PK_T_CODE_INFO primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_PUB_GENDERSERIALNOAUTO...
create table T_PUB_GENDERSERIALNOAUTO
(
  id        VARCHAR2(20) not null,
  todaytime DATE,
  type      VARCHAR2(20),
  subnumber NUMBER,
  remark    VARCHAR2(500),
  newnumber NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
comment on table T_PUB_GENDERSERIALNOAUTO
  is '自增有序主键获取表';
comment on column T_PUB_GENDERSERIALNOAUTO.id
  is '主键id';
comment on column T_PUB_GENDERSERIALNOAUTO.todaytime
  is '时间';
comment on column T_PUB_GENDERSERIALNOAUTO.type
  is '类型标识';
comment on column T_PUB_GENDERSERIALNOAUTO.subnumber
  is '20位截取多少位（后至前）';
comment on column T_PUB_GENDERSERIALNOAUTO.remark
  is '备注';
comment on column T_PUB_GENDERSERIALNOAUTO.newnumber
  is '序号，用于升序，查询后立马加1';
alter table T_PUB_GENDERSERIALNOAUTO
  add constraint PK_GENDERSERIALNOAUTO_ID primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt Creating T_S_DEPT...
create table T_S_DEPT
(
  id              VARCHAR2(32) not null,
  pid             VARCHAR2(32),
  dept_name       VARCHAR2(100),
  remark          VARCHAR2(200),
  create_date     DATE,
  create_userid   VARCHAR2(32),
  create_username VARCHAR2(20),
  modify_date     DATE,
  modify_userid   VARCHAR2(32),
  state           VARCHAR2(1) default '1'
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_S_DEPT
  is '部门表';
comment on column T_S_DEPT.id
  is '主键id';
comment on column T_S_DEPT.pid
  is '上级部门id';
comment on column T_S_DEPT.dept_name
  is '部门名称';
comment on column T_S_DEPT.remark
  is '备注';
comment on column T_S_DEPT.create_date
  is '创建时间';
comment on column T_S_DEPT.create_userid
  is '创建人id';
comment on column T_S_DEPT.create_username
  is '创建人名称';
comment on column T_S_DEPT.modify_date
  is '修改时间';
comment on column T_S_DEPT.modify_userid
  is '修改人id';
comment on column T_S_DEPT.state
  is '状态0：删除、1：正常';
alter table T_S_DEPT
  add constraint PK_T_S_DEPT primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_S_MENU...
create table T_S_MENU
(
  id               VARCHAR2(50) not null,
  module           VARCHAR2(20),
  pid              VARCHAR2(50),
  name_            VARCHAR2(300) default '1',
  url_             VARCHAR2(500),
  type             VARCHAR2(10),
  sys_flag         VARCHAR2(1),
  icon             VARCHAR2(200),
  sort             NUMBER(3),
  remark           VARCHAR2(500),
  state            VARCHAR2(1),
  create_date      DATE,
  create_user      VARCHAR2(50),
  create_user_name VARCHAR2(100),
  modify_date      DATE,
  modify_user      VARCHAR2(50),
  modify_user_name VARCHAR2(100),
  modify_type      VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_S_MENU
  is '菜单功能表';
comment on column T_S_MENU.id
  is '主键id';
comment on column T_S_MENU.module
  is '模块';
comment on column T_S_MENU.pid
  is '上级菜单ID';
comment on column T_S_MENU.name_
  is '显示名称';
comment on column T_S_MENU.url_
  is 'URL的地址';
comment on column T_S_MENU.type
  is '0=功能、1=菜单';
comment on column T_S_MENU.sys_flag
  is '系统标记(0、1)，0= 系统属性(隐藏,页面不展示)    1=系统属性(,不能删除)  ';
comment on column T_S_MENU.icon
  is '显示图片所在路径';
comment on column T_S_MENU.sort
  is '排序';
comment on column T_S_MENU.remark
  is '备注';
comment on column T_S_MENU.state
  is '状态(1-可用,0-不可用)';
comment on column T_S_MENU.create_date
  is '创建日期';
comment on column T_S_MENU.create_user
  is '创建人';
comment on column T_S_MENU.create_user_name
  is '创建人名称';
comment on column T_S_MENU.modify_date
  is '修改日期';
comment on column T_S_MENU.modify_user
  is '修改人';
comment on column T_S_MENU.modify_user_name
  is '修改人姓名';
comment on column T_S_MENU.modify_type
  is '修改类型(create/update/delete)';
alter table T_S_MENU
  add constraint PK_T_S_MENU primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_S_PERMISS...
create table T_S_PERMISS
(
  id              VARCHAR2(32) not null,
  pid             VARCHAR2(32),
  perm_name       VARCHAR2(100) not null,
  remark          VARCHAR2(200),
  create_date     DATE not null,
  create_userid   VARCHAR2(32),
  create_username VARCHAR2(20),
  modify_date     DATE,
  modify_userid   VARCHAR2(32),
  state           VARCHAR2(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
comment on table T_S_PERMISS
  is '权限表';
comment on column T_S_PERMISS.id
  is '主键id';
comment on column T_S_PERMISS.pid
  is '上级权限id';
comment on column T_S_PERMISS.perm_name
  is '权限名称';
comment on column T_S_PERMISS.remark
  is '备注';
comment on column T_S_PERMISS.create_date
  is '创建时间';
comment on column T_S_PERMISS.create_userid
  is '创建人id';
comment on column T_S_PERMISS.create_username
  is '创建人名称';
comment on column T_S_PERMISS.modify_date
  is '修改时间';
comment on column T_S_PERMISS.modify_userid
  is '修改人id';
comment on column T_S_PERMISS.state
  is '状态0：删除、1：正常';
alter table T_S_PERMISS
  add constraint PK_T_S_PERMISS primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt Creating T_S_ROLE...
create table T_S_ROLE
(
  id            VARCHAR2(32) not null,
  role_name     VARCHAR2(50) not null,
  description   VARCHAR2(200),
  create_date   DATE not null,
  create_userid VARCHAR2(32),
  create_user   VARCHAR2(20),
  modify_date   DATE,
  modify_userid VARCHAR2(32),
  state         VARCHAR2(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_S_ROLE
  is '角色表';
comment on column T_S_ROLE.id
  is '主键id';
comment on column T_S_ROLE.role_name
  is '角色名';
comment on column T_S_ROLE.description
  is '角色描述';
comment on column T_S_ROLE.create_date
  is '创建时间';
comment on column T_S_ROLE.create_userid
  is '创建人id';
comment on column T_S_ROLE.create_user
  is '创建人名称';
comment on column T_S_ROLE.modify_date
  is '修改时间';
comment on column T_S_ROLE.modify_userid
  is '修改人id';
comment on column T_S_ROLE.state
  is '状态0：删除、1：正常';
alter table T_S_ROLE
  add constraint PK_T_S_ROLE primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_S_ROLE_PERMREG...
create table T_S_ROLE_PERMREG
(
  id         VARCHAR2(32) not null,
  role_id    VARCHAR2(32) not null,
  perm_id    VARCHAR2(32) not null,
  perm_allow VARCHAR2(1) default '1'
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
comment on table T_S_ROLE_PERMREG
  is '角色关联权限表';
comment on column T_S_ROLE_PERMREG.id
  is '主键id';
comment on column T_S_ROLE_PERMREG.role_id
  is '角色ID';
comment on column T_S_ROLE_PERMREG.perm_id
  is '权限id';
comment on column T_S_ROLE_PERMREG.perm_allow
  is '是否有效：1：有效 0：无效';
alter table T_S_ROLE_PERMREG
  add constraint PK_T_S_ROLE_PERMREG primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

prompt Creating T_S_USER...
create table T_S_USER
(
  id                   VARCHAR2(32) not null,
  user_id              VARCHAR2(30) not null,
  password             VARCHAR2(100) not null,
  dept_id              VARCHAR2(32),
  role_id              VARCHAR2(32),
  real_name            VARCHAR2(20),
  age                  VARCHAR2(1),
  phone                VARCHAR2(30),
  msisdn               VARCHAR2(50),
  addresses            VARCHAR2(200),
  email                VARCHAR2(50),
  city                 VARCHAR2(50),
  province             VARCHAR2(100),
  country              VARCHAR2(100),
  password_hint        VARCHAR2(200),
  account_enabled      VARCHAR2(1) default '1',
  account_expired      VARCHAR2(1) default '1',
  account_locked       VARCHAR2(1) default '1',
  password_error_times NUMBER(8),
  last_login_time      DATE,
  password_error_lock  VARCHAR2(1) default '1',
  rtx_num              VARCHAR2(50),
  remark               VARCHAR2(500),
  state                VARCHAR2(1) default '1',
  create_date          DATE default SYSDATE,
  create_userid        VARCHAR2(32),
  create_username      VARCHAR2(20),
  modify_date          DATE,
  modify_userid        VARCHAR2(32)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_S_USER
  is '用户表';
comment on column T_S_USER.id
  is '主键id';
comment on column T_S_USER.user_id
  is '账号';
comment on column T_S_USER.password
  is '密码';
comment on column T_S_USER.dept_id
  is '部门id';
comment on column T_S_USER.role_id
  is '角色id';
comment on column T_S_USER.real_name
  is '姓名';
comment on column T_S_USER.age
  is '性别：1=男、0=女';
comment on column T_S_USER.phone
  is '联系电话';
comment on column T_S_USER.msisdn
  is '移动电话';
comment on column T_S_USER.addresses
  is '住址';
comment on column T_S_USER.email
  is '电子邮箱 ';
comment on column T_S_USER.city
  is '市';
comment on column T_S_USER.province
  is '省';
comment on column T_S_USER.country
  is '国家';
comment on column T_S_USER.password_hint
  is '密码提示';
comment on column T_S_USER.account_enabled
  is '账号是否可用 0-是 1-否  ';
comment on column T_S_USER.account_expired
  is '账号是否过期 0-是 1-否';
comment on column T_S_USER.account_locked
  is '账号是否锁定 0-是 1-否';
comment on column T_S_USER.password_error_times
  is '记录密码输错次数';
comment on column T_S_USER.last_login_time
  is '记录上一次该账号进行登录的时间';
comment on column T_S_USER.password_error_lock
  is '密码输入错误锁，1为正常，0为锁定';
comment on column T_S_USER.rtx_num
  is 'RTX号码';
comment on column T_S_USER.remark
  is '备注';
comment on column T_S_USER.state
  is '状态0：删除、1：正常';
comment on column T_S_USER.create_date
  is '创建时间';
comment on column T_S_USER.create_userid
  is '创建人id';
comment on column T_S_USER.create_username
  is '创建人名称';
comment on column T_S_USER.modify_date
  is '修改时间';
comment on column T_S_USER.modify_userid
  is '修改人id';
create index INDEX_T_S_USER_ID on T_S_USER (USER_ID, PASSWORD)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table T_S_USER
  add constraint PK_T_S_USER unique (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for T_BASE_FIELD...
alter table T_BASE_FIELD disable all triggers;
prompt Disabling triggers for T_BASE_LOADREDIS...
alter table T_BASE_LOADREDIS disable all triggers;
prompt Disabling triggers for T_CODE_INFO...
alter table T_CODE_INFO disable all triggers;
prompt Disabling triggers for T_PUB_GENDERSERIALNOAUTO...
alter table T_PUB_GENDERSERIALNOAUTO disable all triggers;
prompt Disabling triggers for T_S_DEPT...
alter table T_S_DEPT disable all triggers;
prompt Disabling triggers for T_S_MENU...
alter table T_S_MENU disable all triggers;
prompt Disabling triggers for T_S_PERMISS...
alter table T_S_PERMISS disable all triggers;
prompt Disabling triggers for T_S_ROLE...
alter table T_S_ROLE disable all triggers;
prompt Disabling triggers for T_S_ROLE_PERMREG...
alter table T_S_ROLE_PERMREG disable all triggers;
prompt Disabling triggers for T_S_USER...
alter table T_S_USER disable all triggers;
prompt Deleting T_S_USER...
delete from T_S_USER;
commit;
prompt Deleting T_S_ROLE_PERMREG...
delete from T_S_ROLE_PERMREG;
commit;
prompt Deleting T_S_ROLE...
delete from T_S_ROLE;
commit;
prompt Deleting T_S_PERMISS...
delete from T_S_PERMISS;
commit;
prompt Deleting T_S_MENU...
delete from T_S_MENU;
commit;
prompt Deleting T_S_DEPT...
delete from T_S_DEPT;
commit;
prompt Deleting T_PUB_GENDERSERIALNOAUTO...
delete from T_PUB_GENDERSERIALNOAUTO;
commit;
prompt Deleting T_CODE_INFO...
delete from T_CODE_INFO;
commit;
prompt Deleting T_BASE_LOADREDIS...
delete from T_BASE_LOADREDIS;
commit;
prompt Deleting T_BASE_FIELD...
delete from T_BASE_FIELD;
commit;

prompt Loading T_S_USER...
insert into T_S_USER (id, user_id, password, dept_id, role_id, real_name, age, phone, msisdn, addresses, email, city, province, country, password_hint, account_enabled, account_expired, account_locked, password_error_times, last_login_time, password_error_lock, rtx_num, remark, state, create_date, create_userid, create_username, modify_date, modify_userid)
values ('u_admin', 'admin', '123', 'D167FD414946459094405E0706CF3672', '660241A049E2488BB06C3D5DBD6EE783', '管理员', '1', '123456', null, '广西', null, 'D167FD414946459094405E0706CF3673', null, null, null, '1', '1', '1', null, null, '1', null, null, '1', to_date('27-08-2017', 'dd-mm-yyyy'), 'u_admin', 'admin', to_date('14-04-2018 20:25:16', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
commit;
prompt 1 records loaded
prompt Enabling triggers for T_BASE_FIELD...
alter table T_BASE_FIELD enable all triggers;
prompt Enabling triggers for T_BASE_LOADREDIS...
alter table T_BASE_LOADREDIS enable all triggers;
prompt Enabling triggers for T_CODE_INFO...
alter table T_CODE_INFO enable all triggers;
prompt Enabling triggers for T_PUB_GENDERSERIALNOAUTO...
alter table T_PUB_GENDERSERIALNOAUTO enable all triggers;
prompt Enabling triggers for T_S_DEPT...
alter table T_S_DEPT enable all triggers;
prompt Enabling triggers for T_S_MENU...
alter table T_S_MENU enable all triggers;
prompt Enabling triggers for T_S_PERMISS...
alter table T_S_PERMISS enable all triggers;
prompt Enabling triggers for T_S_ROLE...
alter table T_S_ROLE enable all triggers;
prompt Enabling triggers for T_S_ROLE_PERMREG...
alter table T_S_ROLE_PERMREG enable all triggers;
prompt Enabling triggers for T_S_USER...
alter table T_S_USER enable all triggers;
set feedback on
set define on
prompt Done.
