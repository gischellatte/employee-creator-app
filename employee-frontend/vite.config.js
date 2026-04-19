import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
    test: {
    globals: true, // so we don't have to import in every file
    environment: 'jsdom',
    setupFiles: './config/setup-tests.js',
  }
})
