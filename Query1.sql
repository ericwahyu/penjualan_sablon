create tablespace ericwa
datafile 'D:\backup plekdisk\KULIAH\semester 4\ericwa.dbf'
size 30M;

create user ericwa
IDENTIFIED BY ericwa
DEFAULT TABLESPACE ericwa
QUOTA 30M ON ericwa;

GRANT ALL PRIVILEGES TO ericwa;

conn ericwa/ericwa;

create table jenis_warna (
id_jenis_warna		integer not null,
warna_bahan		varchar(25),
constraint PK_ID_JENIS_WARNA primary key (id_jenis_warna));

create table stock_warna(
id_warna	integer not null,
warna		varchar(25),
harga_warna	number(25),
id_jenis_warna	integer,
constraint PK_ID_WARNA primary key (id_warna),
constraint FK_ID_JENIS_WARNA foreign key (id_jenis_warna)
references jenis_warna (id_jenis_warna));

create table stock_bahan(
id_bahan	integer not null,
nama_bahan	varchar(25),
stok		number(25),
harga_satuan	number(25),
constraint PK_ID_BAHAN primary key (id_bahan));

create table transaksi (
id_transaksi integer	not null,
tgl_transaksi		Date,
total_harga		number(25),
id_bahan		integer,
jumlah_bahan		number(25),
constraint PK_ID_TRANSAKSI primary key (id_transaksi),
constraint FK_ID_BAHAN foreign key (id_bahan)
references stock_bahan (id_bahan));

create table detail_transaksi(
id_transaksi	integer,
id_warna	integer,
constraint FK_ID_TRANSAKSI foreign key (id_transaksi)
references transaksi (id_transaksi),
contraint FK_ID_WARNA foreign key (id_warna)
references stock_warna (id_warna));

create sequence id_jenis_warna
minvalue 1
maxvalue 999999
start with 1
increment by 1
cache 20;

create sequence id_warna
minvalue 1
maxvalue 999999
start with 1
increment by 1
cache 20;

create sequence id_bahan
minvalue 1
maxvalue 999999
start with 1
increment by 1
cache 20;

create sequence id_transaksi
minvalue 1
maxvalue 999999
start with 1
increment by 1
cache 20;

create view list_warna as
select a.warna, a.harga_warna, b.warna_bahan
from stock_warna a join jenis_warna b
on a.id_jenis_warna = b.id_jenis_warna;
