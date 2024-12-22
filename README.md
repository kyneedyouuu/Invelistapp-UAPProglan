# 🏪 Sistem Manajemen Inventaris Gudang Invelist

## 📝 Deskripsi
Invelist adalah sistem manajemen inventaris gudang berbasis Java dengan antarmuka pengguna yang ramah, dibangun menggunakan Java Swing. Aplikasi ini menyediakan tingkat akses berbeda untuk administrator dan pengguna biasa, memungkinkan manajemen inventaris yang aman dan efisien.

## ✨ Fitur-Fitur
- 🔐 Sistem login aman dengan peran admin dan pengguna
- 📦 Manajemen inventaris lengkap (operasi CRUD)
- 🖼️ Dukungan unggah gambar untuk setiap item
- 💰 Format harga otomatis dalam Rupiah
- 🎨 Antarmuka pengguna yang ramah dengan gaya kustom
- 🔢 Validasi input untuk field numerik
- 📊 Tampilan tabel untuk item inventaris

## 🛠️ Teknologi yang Digunakan
- Bahasa: Java
- Framework GUI: Java Swing
- Build Tool: Maven/Gradle
- Framework Testing: JUnit

## 🚀 Memulai

### Prasyarat
- ☕ Java JDK 8 atau lebih tinggi
- 🔧 IDE Java pilihan Anda (Eclipse, IntelliJ IDEA, atau NetBeans)

### Kredensial Login Default
```
👨‍💼 Akses Admin:
Username: admin
Password: admin123

👤 Akses Pengguna:
Username: user
Password: user123
```

### 📥 Instalasi
1. Clone repositori:
```bash
git clone https://github.com/usernamekamu/invelist-warehouse-inventory.git
```
2. Buka proyek di IDE Anda
3. Build proyek
4. Jalankan kelas `Main`

## 🎯 Cara Penggunaan
1. 🔑 Jalankan aplikasi dan login menggunakan kredensial yang disediakan
2. 📋 Untuk Administrator:
    - Menambahkan item baru dengan gambar
    - Memperbarui item yang ada
    - Menghapus item
    - Melihat seluruh inventaris
3. 👥 Untuk Pengguna:
    - Melihat item inventaris
    - Tidak dapat memodifikasi data inventaris

## 📁 Struktur Proyek
```
com.invelistwarehouseinventory
├── 🖥️ ApplicationGUI
│   └── InvelistApp.java
├── 🗄️ Database
│   ├── Item.java
│   └── Login.java
├── ⚠️ Exception
│   └── NumericDocumentFilter.java
└── 🧪 Test
    └── InvelistAppTest.java
```

## ⚡ Komponen Utama
- `InvelistApp`: Jendela aplikasi utama dengan fitur manajemen inventaris
- `Login`: Menangani autentikasi pengguna dan akses berbasis peran
- `Item`: Model data untuk item inventaris
- `NumericDocumentFilter`: Validasi input untuk field numerik

## 🧪 Pengujian
Aplikasi ini mencakup pengujian JUnit untuk fungsi utama:
- ✅ Menambahkan item
- ✅ Memperbarui item
- ✅ Menghapus item
- ✅ Validasi input

Jalankan pengujian menggunakan test runner IDE Anda atau:
```bash
./gradlew test   # Untuk Gradle
mvn test         # Untuk Maven
```

## 👥 Kontribusi
1. Fork repositori
2. Buat branch fitur Anda: `git checkout -b fitur/FiturKeren`
3. Commit perubahan Anda: `git commit -m 'Menambahkan FiturKeren'`
4. Push ke branch: `git push origin fitur/FiturKeren`
5. Buat pull request

## 📄 Lisensi
Proyek ini dilisensikan di bawah Lisensi MIT - lihat file LICENSE untuk detail

## ✍️ Penulis
- Nama Anda - *Pengembangan awal*

## 🙏 Ucapan Terima Kasih
- Terima kasih kepada semua kontributor
- Terinspirasi dari sistem manajemen inventaris modern
- Dibuat dengan ❤️ menggunakan Java Swing
