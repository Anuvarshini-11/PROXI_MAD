# ProxiStudy – Hyperlocal Study Group Finder

## Project Overview
ProxiStudy is a location-based mobile application for students to find and join nearby in-person study sessions. It enforces institutional access (@psgtech.ac.in), manages small group sizes, and provides real-time chat.

## Tech Stack
- **Frontend:** React Native (Expo)
- **Backend:** Node.js, Express.js
- **Database:** MongoDB (2dsphere indexing for location)
- **Real-time:** Socket.io
- **Auth:** JWT & Bcrypt

---

## 🛠 Setup Instructions

### 1. Prerequisites
- Install [Node.js](https://nodejs.org/)
- Install [Expo Go](https://expo.dev/client) app on your physical device (Android/iOS)
- A MongoDB Atlas account or local MongoDB instance

### 2. Backend Setup
1. Open a terminal and navigate to the `backend` folder.
2. Run `npm install` to install dependencies.
3. Create a `.env` file in the `backend` directory (copy from `.env.example` if provided).
   - Set `MONGO_URI` to your MongoDB connection string.
   - Set `JWT_SECRET` to any random string.
4. Start the server:
   - Development: `npm run dev`
   - Production: `npm start`

### 3. Frontend Setup
1. Open a terminal and navigate to the `frontend` folder.
2. Run `npm install` to install dependencies.
3. **CRITICAL:** Open `src/api/api.js` and `src/screens/ChatScreen.js`.
   - Replace `YOUR_IP_ADDRESS` with your computer's local IP address (e.g., `192.168.1.5`).
   - Find your IP on Windows using `ipconfig` or Mac/Linux using `ifconfig`.
4. Start the Expo app: `npm start`
5. Scan the QR code using the Expo Go app on your phone.

---

## 📡 API Endpoints

### Auth
- `POST /api/auth/register` - Create new account
- `POST /api/auth/login` - Login to account

### Sessions
- `POST /api/sessions` - Create a new study session
- `GET /api/sessions/nearby` - Get sessions within 2km (query params: `longitude`, `latitude`)
- `POST /api/sessions/:id/join` - Join a session
- `GET /api/sessions/:id` - Get full details of a session

### Admin (Requires Admin Role)
- `GET /api/admin/users` - View all users
- `GET /api/admin/sessions` - View all sessions
- `DELETE /api/admin/users/:id` - Deactivate user
- `DELETE /api/admin/sessions/:id` - Delete session

---

## 🗄 MongoDB Schema

### User
- `name` (String)
- `email` (String, institutional match)
- `password` (Hashed)
- `rating` (Number, default 0)
- `role` (user/admin)

### Session
- `title`, `subject`, `description`
- `location` (GeoJSON Point: [lng, lat])
- `host` (Ref: User)
- `maxMembers` (4-6)
- `currentMembers` (Array of Ref: User)
- `status` (active/full/completed)

### Message
- `session` (Ref: Session)
- `user` (Ref: User)
- `userName` (String)
- `text` (String)

---

## 🚀 Commands to Run
**Backend:**
```bash
cd backend
npm run dev
```

**Frontend:**
```bash
cd frontend
npm start
```
# Proxi_Mad
# Proxi_Mad
# PROXI_MAD
