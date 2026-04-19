import { cleanup } from "@testing-library/react";
import { afterEach, expect } from 'vitest';
import * as matchers from '@testing-library/jest-dom';
expect.extend(matchers);

afterEach(() => {
  cleanup();
});