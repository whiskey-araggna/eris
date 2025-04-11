# 🛒 ERIS - E-Commerce Project Documentation

_Eris is the MVP e-commerce platform for araggna.space._

## 📦 Tech Stack

- Backend: Java (Spring Boot)
- Frontend: Vaadin Flow
- Database: PostgreSQL
- Auth: SSO via cerberus.araggna.space

---

## 🧠 Database Schema

### Table: `erisuser`

| Column       | Type         | Description       |
|--------------|--------------|-------------------|
| userid       | UUID (PK)    | User ID           |
| useremail    | VARCHAR(150) | Email address     |
| userfullname | VARCHAR(100) | Full name         |
| userpassword | VARCHAR(150) | Hashed password   |
| userrole     | ENUM         | USER / ADMIN      |
| createdby    | UUID         | Created by user   |
| createddate  | DATETIME     | Created timestamp |
| updatedby    | UUID         | Updated by user   |
| updateddate  | DATETIME     | Updated timestamp |
| isactive     | BOOLEAN      | Active status     |

---

### Table: `eriscategory`

| Column                                                   | Type          | Description               |
|----------------------------------------------------------|---------------|---------------------------|
| categoryid                                               | UUID (PK)     | Category ID               |
| categorycode                                             | VARCHAR(10)   | Code for category         |
| categoryname                                             | VARCHAR(100)  | Name of category          |
| categoryreff                                             | UUID          | Self-ref: parent category |
| createdby, createddate, updatedby, updateddate, isactive | same as above |

---

### Table: `erisproduct`

| Column                                                   | Type         |
|----------------------------------------------------------|--------------|
| productid                                                | UUID (PK)    |
| categoryid                                               | UUID         |
| productcode                                              | VARCHAR(10)  |
| productname                                              | VARCHAR(100) |
| productdesc                                              | TEXT         |
| createdby, createddate, updatedby, updateddate, isactive |              |

---

### Table: `erissku`

| Column                                                   | Type                           |
|----------------------------------------------------------|--------------------------------|
| skuid                                                    | UUID (PK)                      |
| productid                                                | UUID (FK to erisproduct)       |
| skucode                                                  | VARCHAR(20) (e.g., KAOS-BLK-M) |
| skuname                                                  | VARCHAR(100) (e.g., Hitam / M) |
| skuprice                                                 | MONEY                          |
| createdby, createddate, updatedby, updateddate, isactive |                                |

---

### Table: `erisstock`

| Column                                                   | Type                 |
|----------------------------------------------------------|----------------------|
| stockid                                                  | UUID (PK)            |
| skuid                                                    | UUID (FK to erissku) |
| stockavailable                                           | INTEGER              |
| stockhold                                                | INTEGER              |
| createdby, createddate, updatedby, updateddate, isactive |                      |

---

### Table: `erisfile`

| Column                                                   | Type                          |
|----------------------------------------------------------|-------------------------------|
| fileid                                                   | UUID (PK)                     |
| filecategory                                             | ENUM (PROD, CAT, etc)         |
| filereff                                                 | UUID (FK to product/category) |
| filepath                                                 | VARCHAR(200)                  |
| createdby, createddate, updatedby, updateddate, isactive |                               |

---

### Table: `erisorder`

| Column                                                   | Type                                                     |
|----------------------------------------------------------|----------------------------------------------------------|
| orderid                                                  | UUID (PK)                                                |
| ordercode                                                | VARCHAR(10)                                              |
| orderstatus                                              | ENUM (CREATED, PAID, CANCEL, PROCESSED, SHIPPED, FINISH) |
| ordertotal                                               | MONEY                                                    |
| createdby, createddate, updatedby, updateddate, isactive |                                                          |

---

### Table: `erisorderdetail`

| Column                                                   | Type      |
|----------------------------------------------------------|-----------|
| orderdetailid                                            | UUID (PK) |
| orderid                                                  | UUID (FK) |
| skuid                                                    | UUID (FK) |
| orderqty                                                 | INTEGER   |
| ordersubtotal                                            | MONEY     |
| createdby, createddate, updatedby, updateddate, isactive |           |

---

### Table: `erisorderstatuslog`

| Column      | Type                  |
|-------------|-----------------------|
| statuslogid | UUID (PK)             |
| orderid     | UUID (FK)             |
| orderstatus | ENUM                  |
| statusnote  | TEXT                  |
| createdby   | UUID (FK to erisuser) |
| createddate | DATETIME              |

---

## 🔗 Relational Diagram

```plaintext
[ eriscategory ] 1---* [ erisproduct ] 1---* [ erissku ] 1---1 [ erisstock ]
                                           \
                                            *---* [ erisorderdetail ] *---1 [ erisorder ]

[ erisorder ] 1---* [ erisorderstatuslog ]
```

---

## 🔄 Checkout Flow (MVP)

```mermaid
flowchart TD
  A[🧍 User Login/Register] --> B[🛍 Pilih Produk dan SKU]
  B --> C[➕ Tambah ke Cart (frontend)]
  C --> D[🧾 Checkout]
  D --> E[📦 Order Created (ORDER CREATED)]
  E --> F[💰 Simulasi Pembayaran Manual]
  F --> G[✔️ Mark as Paid (ORDER PAID)]
  G --> H[🛠 Proses Admin (ORDER PROCESSED)]
  H --> I[🚚 Order Dikirim (ORDER SHIPPED)]
  I --> J[📬 Order Selesai (ORDER FINISH)]
```

---

## 📌 Catatan MVP

- Cart disimpan di browser local storage (client side)
- Belum ada integrasi pembayaran otomatis
- Pengiriman dilakukan secara manual oleh admin
- Admin dapat mengubah status order via backend
- Gambar produk disimpan melalui table `erisfile`

---

## ✍️ Penutup

Dokumentasi ini akan diperbarui seiring berkembangnya fitur Eris. Untuk update selanjutnya bisa mencakup:

- Payment Gateway Integration
- Persistent Cart
- Wishlist
- Ekspedisi / Tracking
- Voucher, Diskon, Promo

---
